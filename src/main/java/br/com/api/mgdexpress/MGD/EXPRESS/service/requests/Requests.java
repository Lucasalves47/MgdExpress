package br.com.api.mgdexpress.MGD.EXPRESS.service.requests;

import br.com.api.mgdexpress.MGD.EXPRESS.Login;
import br.com.api.mgdexpress.MGD.EXPRESS.repository.GerenteRepository;
import br.com.api.mgdexpress.MGD.EXPRESS.service.requests.gsonData.*;
import br.com.api.mgdexpress.MGD.EXPRESS.model.handshake_disput.DtoOrderid;
import com.google.gson.Gson;
import lombok.NoArgsConstructor;
import okhttp3.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static br.com.api.mgdexpress.MGD.EXPRESS.Login.clientId;
import static br.com.api.mgdexpress.MGD.EXPRESS.Login.clientIdDistribuido;

@Service
@NoArgsConstructor
public class Requests {

    private GerenteRepository gerenteRepository = null;

    public Requests(GerenteRepository gerenteRepository) {
        this.gerenteRepository = gerenteRepository;
    }

    public  String stringIds = "";


    public String requestToken(String clientId, String clientSecret){

        OkHttpClient client = new OkHttpClient();
        Gson gson = new Gson();

        String url = "https://merchant-api.ifood.com.br/authentication/v1.0/oauth/token";
        RequestBody requestBody = new FormBody.Builder()
                .add("grantType","client_credentials")
                .add("clientId",clientId)
                .add("clientSecret",clientSecret)
                .build();

        Request request = new Request.Builder().url(url).post(requestBody).addHeader("Content-Type", "application/x-www-form-urlencoded").build();

        try {
            var response = client.newCall(request).execute();

            if(response.isSuccessful()){
                System.out.println("*********** PEGANDO O TOKEN *********");
                System.out.println(response.code());
                System.out.println("**************************************");

                var responseBody = response.body().string();
                var token = gson.fromJson(responseBody, ModelTokenResponse.class);
                return "Bearer "+token.getAccessToken();
            }
            else {
                System.out.println("erro: "+response.code());
                System.out.println("erro: ao pegar o token");

            }

            response.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public String requestListaGerente(String token,String nome,String corporativeName){
         var cliente = new OkHttpClient();

        var  url ="https://merchant-api.ifood.com.br/merchant/v1.0/merchants?page=1&size=10";

        Request request = new Request.Builder().url(url).addHeader("Authorization",token).build();

        try {
            var response = cliente.newCall(request).execute();

            if(response.isSuccessful()){
                System.out.println("************** MERCHANTS *************");
                System.out.println(response.code());
                System.out.println("**************************************");

                var gson = new Gson();
                StringBuilder idIfood = new StringBuilder();
                var lista = Arrays.stream(gson.fromJson(response.body().string(), DtolistaGerente[].class)).toList();

                lista.forEach(item ->{
                    System.out.println("********** DADOS *********");
                    System.out.printf("nome Ifood: %s\nnome:%s\nempresa Ifood: %s\nempresa: %s",item.getName(),nome,item.getCorporateName(),corporativeName);
                    System.out.println("**************************************");
                    if (item.getName().equalsIgnoreCase(nome) && item.getCorporateName().equalsIgnoreCase(corporativeName)){
                        idIfood.append(item.getId());
                    }

                });

                return (!idIfood.isEmpty())?idIfood.toString():"LISTAVAZIA";
            }
            else{
                System.out.println(response.code()+" "+response.message());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;

    }

    public DtoDadosGerente requestDadosGerente(String token,String nome,String CorporativeName){
        var client = new OkHttpClient();

        var url = "https://merchant-api.ifood.com.br/merchant/v1.0/merchants/"+requestListaGerente(token,nome,CorporativeName);

        System.out.println("********** URL *********");
        System.out.println(url);
        System.out.println("**************************************");
        var request = new Request.Builder().addHeader("Authorization",token).url(url).build();

        try {
            var response = client.newCall(request).execute();

            if(response.isSuccessful()){
                System.out.println("********** MERCHANT DETALHES *********");
                System.out.println(response.code());
                System.out.println("**************************************");

                var gson = new Gson();
                return gson.fromJson(response.body().string(), DtoDadosGerente.class);
            }
            else{
                System.out.println(response.code()+" "+response.message());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public List<DtoOrderid> requestPedidosPendentes(Long id){
        var gerente = gerenteRepository.getReferenceById(id);
        var token = gerente.getToken();
        if(token == null){
            gerente.setToken(requestToken(Login.clientId,Login.clientSecret));
            token = gerenteRepository.save(gerente).getToken();
        }
        var client = new OkHttpClient();

        var url = "https://merchant-api.ifood.com.br/order/v1.0/events:polling";

        var request = new Request.Builder().url(url).addHeader("Authorization",token).addHeader("x-polling-merchants",gerente.getMerchantId()).build();

        try {
            var reponse = client.newCall(request).execute();
            if(reponse.isSuccessful()){

                System.out.println("*************** POLLING **************");
                System.out.println(reponse.code());
                System.out.println("**************************************");
                if(reponse.code()!=204){
                    Gson gson= new Gson();
                    return Arrays.stream(gson.fromJson(reponse.body().string(), DtoOrderid[].class)).toList();
                }
                else{
                    System.out.println("esta vazio");
                }
            }
            else{
                gerente.setToken(requestToken(Login.clientId,Login.clientSecret));
                gerenteRepository.save(gerente);
                requestPedidosPendentes(id);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public DtoDetalhesDoPedido requstDetalhes(String pedidoId,Long id){

        var token = gerenteRepository.getReferenceById(id).getToken();

        var client = new OkHttpClient();

        var url = "https://merchant-api.ifood.com.br/order/v1.0/orders/"+pedidoId;

        var request = new Request.Builder().addHeader("Authorization",token).url(url).build();

        try {
            var response = client.newCall(request).execute();

            if(response.isSuccessful()){

                System.out.println("********* DETALHES DO PEDIDO *********");
                System.out.println(response.code());
                System.out.println("**************************************");
                Gson gson = new Gson();

                return gson.fromJson(response.body().string(), DtoDetalhesDoPedido.class);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    public  void acknowledgment(List<DtoOrderid> pedidos,Long id) {
        stringIds = "";
        pedidos.forEach(pedido->{
           stringIds = stringIds+"  { \"id\": \""+ pedido.getId()+"\"},";

        });

        var gerente = gerenteRepository.getReferenceById(id);
        var token = gerente.getToken();

        OkHttpClient client = new OkHttpClient();


        String url = "https://merchant-api.ifood.com.br/order/v1.0/events/acknowledgment";

        stringIds+= "1";


        String json = "["+stringIds.replace(",1","")+"]";

        RequestBody requestBody = RequestBody.create(json, MediaType.parse("application/json"));

        // Construa a requisição
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .addHeader("Authorization",token)
                .build();


        try {
           var response = client.newCall(request).execute();
           if(response.isSuccessful()){

               System.out.println("*********** acknowledgment ***********");
               System.out.println(response.code());
               System.out.println("**************************************");
           }
           else{
               System.out.println("*********** acknowledgment ERRO ***********");
               System.out.println(response.code());
               System.out.println();
               System.out.println("**************************************");
           }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String gererCodigoDeAuthorizacao(){

        var client = new OkHttpClient();
        var gson = new Gson();

        var url = "https://merchant-api.ifood.com.br/authentication/v1.0/oauth/userCode";

        RequestBody requestBody = new FormBody.Builder()
                .add("clientId",clientIdDistribuido)
                .build();

        Request request = new Request.Builder().url(url).post(requestBody).addHeader("Content-Type", "application/x-www-form-urlencoded").build();

        try{
            var response = client.newCall(request).execute();

            if(response.isSuccessful()){

                System.out.println("******** CODIGO DE AUTORIZAÇAO *******");
                System.out.println(response.code());
                System.out.println("**************************************");

                var code = gson.fromJson(response.body().string(), UserCode.class);
                return code.userCode();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;

    }
}

package br.com.api.mgdexpress.MGD.EXPRESS.service.requests;

import br.com.api.mgdexpress.MGD.EXPRESS.repository.GerenteRepository;
import br.com.api.mgdexpress.MGD.EXPRESS.service.requests.gsonData.*;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import okhttp3.*;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
@NoArgsConstructor
public class Requests {

    private GerenteRepository gerenteRepository = null;

    public Requests(GerenteRepository gerenteRepository) {
        this.gerenteRepository = gerenteRepository;
    }



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
                var responseBody = response.body().string();
                var token = gson.fromJson(responseBody, ModelTokenResponse.class);
                return "Bearer "+token.getAccessToken();
            }
            else {
                System.out.println("erro: "+response.code());
                System.out.println("erro: "+response.message());

            }

            response.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public String requestListaGerente(String token){
         var cliente = new OkHttpClient();

        var  url ="https://merchant-api.ifood.com.br/merchant/v1.0/merchants?page=1&size=10";

        Request request = new Request.Builder().url(url).addHeader("Authorization",token).build();

        try {
            var response = cliente.newCall(request).execute();

            if(response.isSuccessful()){
                var gson = new Gson();
                return gson.fromJson(response.body().string(), DtolistaGerente[].class)[0].getId();
            }
            else{
                System.out.println(response.code()+" "+response.message());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;

    }

    public DtoDadosGerente requestDadosGerente(String token){
        var client = new OkHttpClient();

        var url = "https://merchant-api.ifood.com.br/merchant/v1.0/merchants/"+requestListaGerente(token);

        var request = new Request.Builder().addHeader("Authorization",token).url(url).build();

        try {
            var response = client.newCall(request).execute();

            if(response.isSuccessful()){
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
            gerente.setToken(requestToken(gerente.getClientId(),gerente.getClientSecret()));
            token = gerenteRepository.save(gerente).getToken();
        }
        var client = new OkHttpClient();

        var url = "https://merchant-api.ifood.com.br/order/v1.0/events:polling?types=PLC%2CREC%2CCFM&groups=ORDER_STATUS%2CDELIVERY";

        var request = new Request.Builder().url(url).addHeader("Authorization",token).build();

        try {
            var reponse = client.newCall(request).execute();
            if(reponse.isSuccessful()){
                if(reponse.code()!=204){
                    Gson gson= new Gson();
                    return Arrays.stream(gson.fromJson(reponse.body().string(), DtoOrderid[].class)).toList();
                }
                else{
                    System.out.println("esta vazio");
                }
            }
            else{
                gerente.setToken(requestToken(gerente.getClientId(),gerente.getClientSecret()));
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
                Gson gson = new Gson();

                return gson.fromJson(response.body().string(), DtoDetalhesDoPedido.class);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }






}

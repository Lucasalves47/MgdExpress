package br.com.api.mgdexpress.MGD.EXPRESS.controller.ifood;

import br.com.api.mgdexpress.MGD.EXPRESS.model.gerente.Gerente;
import br.com.api.mgdexpress.MGD.EXPRESS.model.pedido.Status;
import br.com.api.mgdexpress.MGD.EXPRESS.repository.GerenteRepository;
import br.com.api.mgdexpress.MGD.EXPRESS.repository.PedidoRepository;
import br.com.api.mgdexpress.MGD.EXPRESS.service.TokenService;
import br.com.api.mgdexpress.MGD.EXPRESS.service.requests.gsonData.DtoCodigosDeCancelamento;
import br.com.api.mgdexpress.MGD.EXPRESS.service.requests.gsonData.UserCode;
import com.google.gson.Gson;
import okhttp3.*;
import okhttp3.RequestBody;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static br.com.api.mgdexpress.MGD.EXPRESS.Login.clientId;


@RestController
@RequestMapping("/ifood")
public class ControllerIfood {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private GerenteRepository gerenteRepository;
    @Autowired
    private PedidoRepository pedidoRepository;
    @GetMapping("/status")
    public DtoStatus status(@RequestHeader("Authorization") String header){

        var token = header.replace("Bearer ","");
        var id = tokenService.getId(token);

        var gerente = gerenteRepository.getReferenceById(id);
        var tokenIfood = gerente.getToken();

        var client = new OkHttpClient();

        var url = "https://merchant-api.ifood.com.br/merchant/v1.0/merchants/f289f44d-32ba-419b-8d95-a847b7f97a0d/status";



        Request request = new Request.Builder().url(url).addHeader("Authorization",tokenIfood).build();

        try {
            var response = client.newCall(request).execute();

            if(response.isSuccessful()){
                String json = response.body().string();

                try {
                    // Parse o JSON
                    JSONArray jsonArray = new JSONArray(json);

                    // Obtém o primeiro objeto JSON
                    JSONObject jsonObject = jsonArray.getJSONObject(0);

                    // Obtém o valor do campo "state"
                    String state = jsonObject.getString("state");

                     return new DtoStatus(state);
                } catch (JSONException e) {

                    return new DtoStatus("jsonerro");
                }
            }
        } catch (IOException e) {
            return new DtoStatus( new RuntimeException(e).getMessage());
        }


        return  new DtoStatus("erro");

    }

    @GetMapping("/confirm/{idProduto}")
    @Transactional
    public ResponseEntity confirme(@RequestHeader("Authorization") String header, @PathVariable String idProduto){

        var token = header.replace("Bearer ","");
        var id = tokenService.getId(token);
        var tokenIfood = gerenteRepository.getReferenceById(id).getToken();

        var client = new OkHttpClient();

        var url = "https://merchant-api.ifood.com.br/order/v1.0/orders/"+idProduto+"/confirm";

        okhttp3.RequestBody requestBody = RequestBody.create("{}", MediaType.parse("application/json"));


        var request = new Request.Builder().url(url).addHeader("Authorization",tokenIfood).post(requestBody).build();

        try {
            var response = client.newCall(request).execute();

            if (response.isSuccessful()){

                System.out.println("*********** confirmar ***********");
                System.out.println(response.code());
                System.out.println("**************************************");
                var pedido = pedidoRepository.findByIdPedidoIfood(idProduto);
                pedido.setStatus(Status.IFOODACEITO);
                return ResponseEntity.ok().build();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("********************************");
        System.out.println("erro na requisiçao");
        System.out.println("********************************");
        return ResponseEntity.badRequest().build();
    }


    @GetMapping("/dispatch/{idPedido}")
    @Transactional
    public ResponseEntity dispatch(@RequestHeader("Authorization") String header,@PathVariable String idPedido){

        var token = header.replace("Bearer ","");
        var id = tokenService.getId(token);

        var gerente = gerenteRepository.getReferenceById(id);

        var client = new OkHttpClient();

        var url = "https://merchant-api.ifood.com.br/order/v1.0/orders/"+idPedido+"/dispatch";

        okhttp3.RequestBody requestBody = RequestBody.create("{}", MediaType.parse("application/json"));


        var request = new Request.Builder().url(url).post(requestBody).addHeader("Authorization", gerente.getToken()).build();

        try {
            var response = client.newCall(request).execute();

            if(response.isSuccessful()){
                System.out.println("*********** DISPACHT ***********");
                System.out.println(response.code());
                System.out.println("**************************************");
                return ResponseEntity.ok().build();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.badRequest().build();

    }

    @GetMapping("/takeout/{idPedido}")
    public ResponseEntity takeout(@RequestHeader("Authorization") String header,@PathVariable String idPedido){

        var token = header.replace("Bearer ","");
        var id = tokenService.getId(token);

        var gerente = gerenteRepository.getReferenceById(id);

        var client = new OkHttpClient();

        var url = "https://merchant-api.ifood.com.br/order/v1.0/orders/"+idPedido+"/readyToPickup";

        okhttp3.RequestBody requestBody = RequestBody.create("{}", MediaType.parse("application/json"));


        var request = new Request.Builder().url(url).post(requestBody).addHeader("Authorization", gerente.getToken()).build();

        try {
            var response = client.newCall(request).execute();

            if(response.isSuccessful()){
                System.out.println("*********** TAKEOUT ***********");
                System.out.println(response.code());
                System.out.println("**************************************");
                return ResponseEntity.ok().build();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.badRequest().build();

    }

    @GetMapping("/apagarPedido/{id}")
    @Transactional
    public ResponseEntity apagarPedido(@PathVariable String id){
        var pedido = pedidoRepository.findByIdPedidoIfood(id);
        pedidoRepository.deleteById(pedido.getId());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("cancelarPedidoIfoodMotivos/{idPedido}")
    public ResponseEntity<List<DtoCodigosDeCancelamento>> cancelarPedido(@RequestHeader("Authorization") String header, @PathVariable String idPedido){

        var token = header.replace("Bearer ","");
        var id = tokenService.getId(token);
        var gerente = gerenteRepository.getReferenceById(id);
        var tokenIfood = gerente.getToken();

        var cliente = new OkHttpClient();

        var url = "https://merchant-api.ifood.com.br/order/v1.0/orders/"+idPedido+"/cancellationReasons";
        var request = new Request.Builder().url(url).addHeader("Authorization",tokenIfood).build();

        try{
            var response = cliente.newCall(request).execute();

            if(response.isSuccessful()){
                var gson = new Gson();
                var codigos = gson.fromJson(response.body().string(), DtoCodigosDeCancelamento[].class);
                return ResponseEntity.ok().body(Arrays.stream(codigos).toList());
            }
            else{
                System.out.println("***************************************");
                System.out.println(response.body().string());
                System.out.println(response.code());
                System.out.println(response.message());
                System.out.println("***************************************");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;

    }


    @GetMapping("/cancelar/{idPedido}/{dadosCancelamento}")
    public ResponseEntity cancelar(@PathVariable String idPedido,@PathVariable String dadosCancelamento,@RequestHeader("Authorization") String header){

        var token = header.replace("Bearer ","");
        var id = tokenService.getId(token);
        var gerente = gerenteRepository.getReferenceById(id);
        var tokenIfood = gerente.getToken();

        var cliente = new OkHttpClient();

        var dados = dadosCancelamento.split("°-°");

        var url = "https://merchant-api.ifood.com.br/order/v1.0/orders/"+idPedido+"/requestCancellation";

        okhttp3.RequestBody requestBody = RequestBody.create("{\"reason\": \""+dados[1]+"\",\"cancellationCode\": \""+dados[0]+"\"}", MediaType.parse("application/json"));


        var request = new Request.Builder().url(url).addHeader("Authorization",tokenIfood).post(requestBody).build();

        try{
            var response = cliente.newCall(request).execute();

            if(response.isSuccessful()){
                System.out.println("*********** CANCELAR ***********");
                System.out.println(response.code());
                System.out.println("**************************************");

                return ResponseEntity.accepted().build();
            }
            else{
                System.out.println("***************************************");
                System.out.println(response.body().string());
                System.out.println(response.code());
                System.out.println(response.message());
                System.out.println("***************************************");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.badRequest().build();
    }



}

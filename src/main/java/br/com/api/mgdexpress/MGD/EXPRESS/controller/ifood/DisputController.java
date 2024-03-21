package br.com.api.mgdexpress.MGD.EXPRESS.controller.ifood;

import br.com.api.mgdexpress.MGD.EXPRESS.model.handshake_disput.Dtos.DtoHandeShaker;
import br.com.api.mgdexpress.MGD.EXPRESS.model.handshake_disput.Metadata;
import br.com.api.mgdexpress.MGD.EXPRESS.repository.GerenteRepository;
import br.com.api.mgdexpress.MGD.EXPRESS.repository.HandeShakeDisputRepository;
import br.com.api.mgdexpress.MGD.EXPRESS.repository.PedidoRepository;
import br.com.api.mgdexpress.MGD.EXPRESS.service.TokenService;
import br.com.api.mgdexpress.MGD.EXPRESS.service.requests.gsonData.DtoAlternatives;
import br.com.api.mgdexpress.MGD.EXPRESS.service.requests.gsonData.DtoMaxAmount;
import br.com.api.mgdexpress.MGD.EXPRESS.service.requests.gsonData.DtoMetadata;
import com.google.gson.Gson;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/plataformaDenegociaçaoPedido")
public class DisputController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private HandeShakeDisputRepository handeShakeDisputRepository;
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private GerenteRepository gerenteRepository;
    @GetMapping("/aceitar/{disputeId}")
    public ResponseEntity aceitar(@PathVariable String disputeId, @RequestHeader("Authorization") String header){

        var token = header.replace("Bearer ","");
        var id = tokenService.getId(token);
        var gerente = gerenteRepository.getReferenceById(id);
        var tokenIfood = gerente.getToken();

        var cliente = new OkHttpClient();



        var url = "https://merchant-api.ifood.com.br/order/v1.0/disputes/"+disputeId+"/accept";

        okhttp3.RequestBody requestBody = RequestBody.create("{}", MediaType.parse("application/json"));


        var request = new Request.Builder().url(url).addHeader("Authorization",tokenIfood).post(requestBody).build();

        try{
            var response = cliente.newCall(request).execute();

            if(response.isSuccessful()){
                System.out.println("***************************************");
                System.out.println("deu certo pedido cancelado");
                System.out.println("***************************************");

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

    @PostMapping("/rejeitar/{disputeId}/{reson}")
    public ResponseEntity rejeitar(@PathVariable String disputeId, @RequestHeader("Authorization") String header, @PathVariable String reson){

        var token = header.replace("Bearer ","");
        var id = tokenService.getId(token);
        var gerente = gerenteRepository.getReferenceById(id);
        var tokenIfood = gerente.getToken();

        var cliente = new OkHttpClient();



        var url = "https://merchant-api.ifood.com.br/order/v1.0/disputes/"+disputeId+"/reject";

        okhttp3.RequestBody requestBody = RequestBody.create("{\"reason\": \" "+reson+"\"}", MediaType.parse("application/json"));


        var request = new Request.Builder().url(url).addHeader("Authorization",tokenIfood).post(requestBody).build();

        try{
            var response = cliente.newCall(request).execute();

            if(response.isSuccessful()){
                System.out.println("***************************************");
                System.out.println("deu certo pedido cancelado");
                System.out.println("***************************************");

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

    @PostMapping("/alternative/{disputeId}/{alternativeId}/{type}")
    public ResponseEntity alternative(@PathVariable String disputeId,@PathVariable String alternativeId, @RequestHeader("Authorization") String header, @PathVariable String type){

        var token = header.replace("Bearer ","");
        var id = tokenService.getId(token);
        var gerente = gerenteRepository.getReferenceById(id);
        var tokenIfood = gerente.getToken();

        var value = new StringBuilder();
        var corrence = new StringBuilder();
        handeShakeDisputRepository.getReferenceById(disputeId).getMetadata().getAlternatives().forEach(alternative->{
            if (alternative.getId().equals(alternativeId)){
                value.append(alternative.getMetadata().getMaxAmount().getValue());
                corrence.append(alternative.getMetadata().getMaxAmount().getCurrency());
            }
        });



        var cliente = new OkHttpClient();


        var alternative = new DtoAlternatives(type,new DtoMetadata(new DtoMaxAmount(value.toString(),corrence.toString())));
        var url = "https://merchant-api.ifood.com.br/order/v1.0/disputes/"+disputeId+"/alternatives/"+alternativeId;

        var json = new Gson().toJson(alternative,DtoAlternatives.class);
        okhttp3.RequestBody requestBody = RequestBody.create(json, MediaType.parse("application/json"));


        var request = new Request.Builder().url(url).addHeader("Authorization",tokenIfood).post(requestBody).build();

        try{
            var response = cliente.newCall(request).execute();

            if(response.isSuccessful()){
                System.out.println("***************************************");
                System.out.println("deu certo pedido cancelado");
                System.out.println("***************************************");

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

    @GetMapping("/listar")
    public ResponseEntity<List<DtoHandeShaker>> listarHandshake_disput(@RequestHeader("") String header){
        var token = header.replace("","");
        var id = tokenService.getId(token);
        List<DtoHandeShaker> lista = new ArrayList<>();

        handeShakeDisputRepository.buscarHandeShakeById(id).forEach(hande->{
            var pedido = pedidoRepository.findByIdPedidoIfood(hande.getOrderId());

            lista.add(new DtoHandeShaker(hande.getId(),pedido.getNomePedido(),tradutorAction(hande.getMetadata().getAction())));
        });

        return ResponseEntity.ok(lista);
    }

    public String tradutorAction(String action){
        switch (action){
            case "CANCELLATION":
                return "Cancelamento Total";
            case "PARTIAL_CANCELLATION":
                return "Cancelamento Parcial";
            case "PROPOSED_AMOUNT_REFUND":
                return "Proposta de Reembouso";
            default:
                return "";
        }
    }

}

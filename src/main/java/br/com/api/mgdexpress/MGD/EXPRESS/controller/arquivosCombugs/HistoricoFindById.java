package br.com.api.mgdexpress.MGD.EXPRESS.controller.arquivosCombugs;

import br.com.api.mgdexpress.MGD.EXPRESS.model.historico.motoboy.DadosHistoricoMotoboy;
import br.com.api.mgdexpress.MGD.EXPRESS.repository.HistoricoRepository;
import br.com.api.mgdexpress.MGD.EXPRESS.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/historico/pedido/")
public class HistoricoFindById {

    @Autowired
    TokenService tokenService;
    @Autowired
    HistoricoRepository historicoRepository;

    @PreAuthorize("hasRole('ROLE_USER_MOTOBOY')")
    @GetMapping("{idpedido}")
    public ResponseEntity<DadosHistoricoMotoboy> buscarPeloIdPedido(@PathVariable Long idpedido, @RequestHeader("Authorization") String header){
        var token = header.replace("Bearer ","");
        var id = tokenService.getId(token);
        System.out.println(idpedido);
        var pedido = new DadosHistoricoMotoboy(historicoRepository.BuscarProIdPedido(idpedido,id));
        return ResponseEntity.ok(pedido);
    }
}
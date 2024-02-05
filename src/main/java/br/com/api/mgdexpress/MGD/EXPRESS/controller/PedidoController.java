package br.com.api.mgdexpress.MGD.EXPRESS.controller;

import br.com.api.mgdexpress.MGD.EXPRESS.controller.listaLocalizacao.ListaLocalizacao;
import br.com.api.mgdexpress.MGD.EXPRESS.model.historico.Historico;
import br.com.api.mgdexpress.MGD.EXPRESS.model.pedido.*;
import br.com.api.mgdexpress.MGD.EXPRESS.repository.*;
import br.com.api.mgdexpress.MGD.EXPRESS.service.RegrasService;
import br.com.api.mgdexpress.MGD.EXPRESS.service.TokenService;
import br.com.api.mgdexpress.MGD.EXPRESS.service.requests.Requests;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private MotoboyRepository motoboyRepository;
    @Autowired
    private HistoricoRepository historicoRepository;
    @Autowired
    private GerenteRepository gerenteRepository;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ListaLocalizacao listaLocalizacao;

    @PreAuthorize("hasRole('ROLE_USER_MASTER') OR hasRole('ROLE_USER_GERENTE')")
    @PostMapping
    @Transactional
    public ResponseEntity criar(@Valid @RequestBody DadosPedido dadosPedido, UriComponentsBuilder uriComponentsBuilder,@RequestHeader("Authorization") String header){
        var token = header.replace("Bearer ","");
        var subject = tokenService.getSubject(token);
        var gerente = gerenteRepository.findByEmail(subject);
        var pedido = pedidoRepository.save(new Pedido(dadosPedido,gerente));
        var uri = uriComponentsBuilder.path("/pedidos/{id}").buildAndExpand("id",pedido.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PreAuthorize("hasRole('ROLE_USER_MOTOBOY')")
    @GetMapping("/mudarEstadopedido/{idPedido}/{distancia}")
    @Transactional
    public ResponseEntity MudarStadoDoPedido(@PathVariable Long idPedido,@RequestHeader("Authorization") String header,@PathVariable Double distancia){

        System.out.println("Entrei no mudar estado do pedido");

        var token = header.replace("Bearer ","");
        var id = tokenService.getId(token);

        var pedido = pedidoRepository.getReferenceById(idPedido);
        var motoboy = motoboyRepository.getReferenceById(id);
        var regras = new RegrasService();

        if(pedido.getStatus() == Status.INICIAR) {
            regras.verificarSeMotoboyDisponivel(motoboy);
            pedido.setStatus(Status.ANDAMENTO);
            pedido.setDistancia(distancia);
            motoboy.setDisponivel(false);
            motoboy.setEmailGerente(pedido.getGerente().getEmail());
            pedidoRepository.save(pedido);
            motoboyRepository.save(motoboy);
            listaLocalizacao.setStatus(id,pedido.getGerente().getEmail());

            pedidoRepository.save(pedido);
            motoboyRepository.save(motoboy);
//            System.out.println("Entrei no mudar estado do pedido Em Andamento");

            return ResponseEntity.ok().build();
        }
        else{
            listaLocalizacao.setStatus(id,"");
            historicoRepository.save(new Historico(pedido));
            pedido.setStatus(Status.FINALIZADO);
            motoboy.setDisponivel(true);
            motoboy.setEmailGerente("");

            return ResponseEntity.noContent().build();
        }
    }

    @PreAuthorize("hasRole('ROLE_USER_MASTER') OR hasRole('ROLE_USER_GERENTE')")
    @GetMapping("/pendente/gerente")
    public ResponseEntity<Page<DadosPedidoPage>> listar(@RequestHeader("Authorization") String header,@PageableDefault(size = 7) Pageable pageable){
//        System.out.println("Entrei no pedido pendente gerente");
        var token = header.replace("Bearer ","");
        var subject = tokenService.getSubject(token);
        var id = tokenService.getId(token);
        var gerente = gerenteRepository.getReferenceById(id);

        var request = new Requests(gerenteRepository);

        var pedidos = request.requestPedidosPendentes(id);
        if(pedidos != null){
            pedidos.forEach(pedidoid ->{
                if(pedidoRepository.findByIdPedidoIfood(pedidoid.getOrderId()) == null) {
                    var pedidoIfood = request.requstDetalhes(pedidoid.getOrderId(), id);
                    pedidoRepository.save(new Pedido(pedidoIfood, gerente.getToken(), gerente));
                }
            });
        }
        var page= pedidoRepository.findAllByLogin(subject,pageable).map(DadosPedidoPage::new);

        return ResponseEntity.ok(page);

    }

    @GetMapping("/pendente")
    public ResponseEntity<List<DadosPedidoPage>> listarPedidodsByMotoboy(@RequestHeader("Authorization") String header) {
//        System.out.println("Entrei no pendente");

        var token = header.replace("Bearer ", "");
        var subject = tokenService.getSubject(token);
        var id = tokenService.getId(token);
        if (motoboyRepository.findByEmail(subject).getDisponivel()) {
            return ResponseEntity.ok(pedidoRepository.findAllWhereStatusINICIAR(id).stream().map(DadosPedidoPage::new).toList());
        }
        return  ResponseEntity.ok(pedidoRepository.findByEmailMotoboy(subject).stream().map(DadosPedidoPage::new).toList());
    }

    @GetMapping("/joinMotoboy_pedido/{idPedido}/{idMotoboy}")
    public ResponseEntity joinMotoboy_Pedido(@PathVariable Long idPedido,@PathVariable Long idMotoboy ){
        var pedido = pedidoRepository.getReferenceById(idPedido);
        pedido.setMotoboy(motoboyRepository.getReferenceById(idMotoboy));
        pedidoRepository.save(pedido);
        return ResponseEntity.ok().build();
    }


    @PreAuthorize("hasRole('ROLE_USER_MASTER') OR hasRole('ROLE_USER_GERENTE')")
    @GetMapping("/EmAndamento")
    public ResponseEntity<Page<DadosPedidoPageEmandamento>> listarPedidosEmAndamento(@PageableDefault(size = 10)Pageable page){
//        System.out.println("Entrei no AAndamento");
        return ResponseEntity.ok(pedidoRepository.findAllWhereStatusANDAMENTO(page).map(DadosPedidoPageEmandamento::new));

    }


    @GetMapping("/{id}")
    public ResponseEntity detalherPedido(@PathVariable Long id){
//        System.out.println("Entrei no detar pedido id");
        var pedido = pedidoRepository.getReferenceByIdAndNotinHistorico(id);
        if(pedido != null) {
            if (pedido.getMotoboy() != null) {
                return ResponseEntity.ok(new DadosPedidoCompleto(pedido));
            }
            return ResponseEntity.ok(new DadosPedidoCompletoSemMotoboy(pedido));
        }else return ResponseEntity.notFound().build();
    }
    @GetMapping("test/{id}")
    public ResponseEntity<DadosPedidoCompleto> testPedido(@PathVariable Long id){
//        System.out.println("Entrei no detar pedido id");
        var pedido = pedidoRepository.getReferenceByIdAndNotinHistorico(id);

        return ResponseEntity.ok(new DadosPedidoCompleto(pedido));
    }
}

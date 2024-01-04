package br.com.api.mgdexpress.MGD.EXPRESS.site;

import br.com.api.mgdexpress.MGD.EXPRESS.repository.HistoricoRepository;
import br.com.api.mgdexpress.MGD.EXPRESS.repository.PedidoRepository;
import br.com.api.mgdexpress.MGD.EXPRESS.service.TokenService;
import br.com.api.mgdexpress.MGD.EXPRESS.site.pageService.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/site/gerente")
public class ControllerSiteGerente {

    private String url = "https://mgdexpressapi-production.up.railway.app";

    @Autowired
    private HistoricoRepository historicoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private TokenService tokenService;

    @GetMapping
    public String mainHtml(){
        return MainHtml.html();
    }

    @PreAuthorize("hasRole('ROLE_USER_MASTER') OR hasRole('ROLE_USER_GERENTE')")
    @GetMapping("/sucesso")
    public ResponseEntity<HtmlPage> sucesso(){
        return ResponseEntity.ok(new HtmlPage(Sucesso.sucesso()));
    }


    @GetMapping("/cadastro/pendente")
    public String pendente(){
        return CadastroGerentePendente.page();
    }


    @PreAuthorize("hasRole('ROLE_USER_MASTER') OR hasRole('ROLE_USER_GERENTE')")
    @GetMapping("/home")
    public ResponseEntity<HtmlPage> home(){
        return ResponseEntity.ok(new HtmlPage(Home.home(url)));
    }

    @PreAuthorize("hasRole('ROLE_USER_MASTER') OR hasRole('ROLE_USER_GERENTE')")
    @GetMapping("/criar")
    public ResponseEntity<HtmlPage> formulario(){

        return ResponseEntity.ok(new HtmlPage(Formulario.formulario()));
    }
    @PreAuthorize("hasRole('ROLE_USER_MASTER') OR hasRole('ROLE_USER_GERENTE')")
    @GetMapping("/meusPedidos")
    public ResponseEntity<HtmlPage> listarMeusPedidos(){
        return  ResponseEntity.ok(new HtmlPage(ListarMeusPedidos.listar()));
    }

    @PreAuthorize("hasRole('ROLE_USER_MASTER') OR hasRole('ROLE_USER_GERENTE')")
    @GetMapping("/historico")
    public ResponseEntity<HtmlPage> listaHistoricos(@RequestHeader("Authorization") String header){
        var token = header.replace("Bearer ","");
        var subject = tokenService.getSubject(token);


        return ResponseEntity.ok(new HtmlPage(ListarHistorico.historocos(subject)));
    }

    @PreAuthorize("hasRole('ROLE_USER_MASTER') OR hasRole('ROLE_USER_GERENTE')")
    @GetMapping("historico/detalhes/{id}")
    public ResponseEntity<HtmlPage> detalharHistorico(@PathVariable Long id){
       var historico = historicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new HtmlPage(Detalhehistorico.detalhar(historico)));
       // return ResponseEntity.ok(new HtmlPage(EmConstrucao.html()));
    }

    @PreAuthorize("hasRole('ROLE_USER_MASTER') OR hasRole('ROLE_USER_GERENTE')")
    @GetMapping("pedido/detalhes/{id}")
    public ResponseEntity<HtmlPage> detalharPedido(@PathVariable Long id){
        var pedido = pedidoRepository.getReferenceById(id);
        return ResponseEntity.ok(new HtmlPage(DetalhePedido.detalhar(pedido)));
       // return ResponseEntity.ok(new HtmlPage(EmConstrucao.html()));
    }



    @GetMapping("solicitacao/cadastro")
    public String FormularioSolicitacaoCadastro(){
        return FormularioSolicitacaoCadastroGerente.html();
    }
}

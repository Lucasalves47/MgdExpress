package br.com.api.mgdexpress.MGD.EXPRESS.controller;

import br.com.api.mgdexpress.MGD.EXPRESS.controller.listaLocalizacao.ListaLocalizacao;
import br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy.*;
import br.com.api.mgdexpress.MGD.EXPRESS.repository.MotoboyRepository;
import br.com.api.mgdexpress.MGD.EXPRESS.repository.UserRepository;
import br.com.api.mgdexpress.MGD.EXPRESS.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("motoboy")

public class MotoboyController {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserRepository useRepository;

    @Autowired
    private MotoboyRepository motoboyRepository;

    @Autowired
    private ListaLocalizacao listaLocalizacao;


    @PreAuthorize("hasRole('ROLE_USER_MASTER')")
    @GetMapping
    public ResponseEntity ListarMotoboys(@PageableDefault(size = 10) Pageable page){
        return ResponseEntity.ok(motoboyRepository.findAll(page).map(DadosMotoboyList::new));
    }

    @PreAuthorize("hasRole('ROLE_USER_MASTER')")
    @GetMapping("/EmEntregas&Disponivel")
    public ResponseEntity ListarMotoboysLocalizacao(){
        List<DadosCadastroListaSemColcheteNoJsom> lista = new ArrayList<>();


        /*listaLocalizacao.getListaLocalizacao().forEach(motoboy->{
            if(motoboy !=null && motoboy.ultimaAtualizacao() != null) {
                long minutosDediferencao = ChronoUnit.MINUTES.between(motoboy.ultimaAtualizacao(), LocalDateTime.now());
                if (minutosDediferencao > 10) {
                    listaLocalizacao.setListaLocalizacao(new DadosLocalizacaoMotoboy(new Localizacao(null, null)), motoboy.id(), motoboy.nome());
                }
            }
        });*/

        listaLocalizacao.getListaLocalizacao().forEach(item ->{
            if(!Objects.isNull(item)){

                lista.add(new DadosCadastroListaSemColcheteNoJsom(item));
            }
        });

        System.out.println("______________________________________________");
        lista.forEach(System.out::println);
        System.out.println("______________________________________________");
        return ResponseEntity.ok(lista);
    }


    @PreAuthorize("hasRole('ROLE_USER_MASTER') OR hasRole('ROLE_USER_GERENTE')")
    @GetMapping("/EmEntregas/gerente")
    public ResponseEntity<List<DadosMotoboyEmEntregaToGerente>> ListarMotoboysEmEntregasByGerente(@RequestHeader("Authorization") String header){

        var token = header.replace("Bearer ","");
        var subject = tokenService.getSubject(token);

        List<DadosMotoboyEmEntregaToGerente> lista = new ArrayList<>();

        listaLocalizacao.getListaLocalizacao().forEach(item ->{

            if(!(item == null)){

                if(!item.disponivel() && item.emailGerente().equals(subject)) {
                    lista.add(new DadosMotoboyEmEntregaToGerente(item));
                }
            }

        });

        return !lista.isEmpty() ? ResponseEntity.ok(lista) :ResponseEntity.noContent().build();


    }


    @PreAuthorize("hasRole('ROLE_USER_MOTOBOY')")
    @PostMapping("/localizacao")
    public ResponseEntity UpLocalizacao(@RequestBody DadosLocalizacaoMotoboy dados,@RequestHeader("Authorization") String header){

        var token = header.replace("Bearer ","");
        var id = tokenService.getId(token);
        var nome = tokenService.getNome(token);
        System.out.println("Up");

        listaLocalizacao.setListaLocalizacao(dados,id,nome);


        //messagingTemplate.convertAndSend("/topic/localizacao", listaLocalizacao);
        return ResponseEntity.ok().build();
    }


    @PreAuthorize("hasRole('ROLE_USER_MASTER')")
    @GetMapping("/motoboyLocalizacao/{id}")
    public ResponseEntity<List<DadosMotoboyList>> MotoboysPorId(@PathVariable Long id){
        List<DadosMotoboyList> lista = new ArrayList<>();
        System.out.println(id);
        listaLocalizacao.getListaLocalizacao().forEach(motoboy ->{
            System.out.println(motoboy);

            if(motoboy != null && Objects.equals(motoboy.id(),id)){
                lista.add(motoboy);
            }
        });

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/setAtivoInativo")
    public ResponseEntity setAtivoInativo(@RequestHeader("Authorization") String header){
        System.out.println("set Ativo Inativo");
        var token = header.replace("Bearer ","");
        var id = tokenService.getId(token);

        var motoboy = motoboyRepository.getReferenceById(id);
        motoboy.setLocalizacao(new Localizacao(null,null));

        listaLocalizacao.getListaLocalizacao().set(id.intValue(),new DadosMotoboyList(motoboy));

        listaLocalizacao.getListaLocalizacao().forEach(System.out::println);
        return ResponseEntity.ok().build();
    }
}

package br.com.api.mgdexpress.MGD.EXPRESS.controller;

import br.com.api.mgdexpress.MGD.EXPRESS.model.gerente.Gerente;
import br.com.api.mgdexpress.MGD.EXPRESS.model.historico.DadosListaHistoricoEntregasDoDia;
import br.com.api.mgdexpress.MGD.EXPRESS.model.historico.ListaDeMesHistorico;
import br.com.api.mgdexpress.MGD.EXPRESS.model.users.User;
import br.com.api.mgdexpress.MGD.EXPRESS.repository.*;
import br.com.api.mgdexpress.MGD.EXPRESS.service.EmailService;
import br.com.api.mgdexpress.MGD.EXPRESS.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/gerente")
public class GerenteController {

    @Autowired
    private GerenteRepository gerenteRepository;

    @Autowired
    private GerenteTemporarioRepository gerenteTemporarioRepository;

    @Autowired
    private EmailService emailService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MotoboyRepository motoboyRepository;
    @Autowired
    private HistoricoRepository historicoRepository;

    @Autowired
    private TokenService tokenService;

    @PreAuthorize("hasRole('ROLE_USER_MASTER')")
    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity criarGerente(@PathVariable Long id){
        var dadosGerente = gerenteTemporarioRepository.getReferenceById(id);

        var bcrypt = new BCryptPasswordEncoder();
        var senha = bcrypt.encode(dadosGerente.getSenha());
        var gerente = gerenteRepository.save(new Gerente(dadosGerente));
        userRepository.save(new User(null,gerente.getId(), dadosGerente.getNome(), dadosGerente.getEmail(), senha,"ROLE_USER_GERENTE"));

        gerenteTemporarioRepository.deleteById(id);

        try {
            emailService.enviarEmailSimples(dadosGerente.getEmail(), "Cadastro MGD EXPRESS","""
                    Parabens!!!
                    """+ dadosGerente.getNome()+"""
                    seu usuario foi cadastrado com sucesso, 
                    Ficamos muito felizes de ter você no time""");
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao enviar e-mail: " + e.getMessage());
        }

    }

    @GetMapping("/listarMotoboysEntregas")
    public ResponseEntity<List<DadosListaHistoricoEntregasDoDia>> listaHistoricoEntregas(@RequestHeader("Authorization") String header){
        List<DadosListaHistoricoEntregasDoDia> listaDeHistorico = new ArrayList<>();
        List<Integer> entregas = new ArrayList<>(Collections.nCopies(motoboyRepository.encontrarMaiorId().intValue() + 1, null));


        var token = header.replace("Bearer ","");
        var email = tokenService.getSubject(token);
        var historicos = historicoRepository.BuscarPorEmailGerente(email);

        historicos.forEach(historico -> {
            var id = historico.getMotoboy().getId().intValue();
            if(entregas.get(id) == null){
                entregas.set(id,1);
            }else{
                entregas.set(id, entregas.get(id)+1);
            }
        });


        historicos.forEach(historico -> {
            var id = historico.getMotoboy().getId().intValue();
            if(historico.getDataEntrega().getDayOfMonth() == LocalDate.now().getDayOfMonth()){
                if(!listaDeHistorico.contains(new DadosListaHistoricoEntregasDoDia(historico, entregas.get(id)))){
                    listaDeHistorico.add(id,new DadosListaHistoricoEntregasDoDia(historico, entregas.get(id)));
                }
            }
        });

        return ResponseEntity.ok(listaDeHistorico);
    }

}


package br.com.api.mgdexpress.MGD.EXPRESS.controller;

import br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy.DadosMotoboyList;
import br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy.Motoboy;
import br.com.api.mgdexpress.MGD.EXPRESS.model.users.User;
import br.com.api.mgdexpress.MGD.EXPRESS.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("master")
public class masterController {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private MotoboyRepository motoboyRepository;
    @Autowired
    private HistoricoRepository historicoRepository;
    @Autowired
    private GerenteRepository gerenteRepository;
    @Autowired
    private UserRepository userRepository;

    @PreAuthorize("hasRole('ROLE_USER_MASTER')")
    @GetMapping("/motoboy")
    public ResponseEntity ListarMotoboys(){
        return ResponseEntity.ok(motoboyRepository.findAll());
    }

    @PreAuthorize("hasRole('ROLE_USER_MASTER')")
    @GetMapping("/gerente")
    public ResponseEntity ListarGerentes(){
        return ResponseEntity.ok(gerenteRepository.findAll());
    }

    @PreAuthorize("hasRole('ROLE_USER_MASTER')")
    @GetMapping("/motoboy/{id}")
    public ResponseEntity<Motoboy> MotoboysPorId(@PathVariable Long id){
        return ResponseEntity.ok(motoboyRepository.getReferenceById(id));
    }



}

package br.com.api.mgdexpress.MGD.EXPRESS.controller.listaLocalizacao;

import br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy.DadosLocalizacaoMotoboy;
import br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy.DadosMotoboyList;
import br.com.api.mgdexpress.MGD.EXPRESS.repository.MotoboyRepository;
import br.com.api.mgdexpress.MGD.EXPRESS.repository.UserRepository;
import br.com.api.mgdexpress.MGD.EXPRESS.service.TokenService;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

@Component
@Scope("application")
public class ListaLocalizacao {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MotoboyRepository motoboyRepository;


    @Getter
    private List<DadosMotoboyList> listaLocalizacao;

    @PostConstruct
    private void initialize() {
        listaLocalizacao = new ArrayList<>(Collections.nCopies(motoboyRepository.encontrarMaiorId().intValue() + 1, null));
       motoboyRepository.findAllAtivos().forEach(motoboy ->{
            listaLocalizacao.set(motoboy.getId().intValue(),new DadosMotoboyList(motoboy));
            System.out.println(listaLocalizacao.get(motoboy.getId().intValue()));
        });
    }

    public void inicializar(){
        listaLocalizacao = new ArrayList<>(Collections.nCopies(motoboyRepository.encontrarMaiorId().intValue() + 1, null));
        motoboyRepository.findAllAtivos().forEach(motoboy -> listaLocalizacao.set(motoboy.getId().intValue(),new DadosMotoboyList(motoboy)));
    }



    public void setListaLocalizacao(DadosLocalizacaoMotoboy lista, Long id, String nome) {

        if (Objects.isNull(listaLocalizacao)) {
            initialize();
        }

        var dadosMotoboyList = listaLocalizacao.get(id.intValue());
        listaLocalizacao.set(id.intValue(), new DadosMotoboyList(id, nome, lista.localizacao(), dadosMotoboyList.disponivel(), dadosMotoboyList.emailGerente(), LocalDate.now()));

        System.out.println(listaLocalizacao.get(id.intValue()).localizacao().getLatitude());
        System.out.println(listaLocalizacao.get(id.intValue()).localizacao().getLongitude());
    }

    public void setStatus(Long id,String email) {
        var dados = listaLocalizacao.get(id.intValue());
        if (dados.disponivel()) {
            listaLocalizacao.set(id.intValue(), new DadosMotoboyList(id, dados.nome(), dados.localizacao(), false,email,dados.ultimaAtualizacao()));
        } else {
            listaLocalizacao.set(id.intValue(), new DadosMotoboyList(id, dados.nome(), dados.localizacao(), true,email,dados.ultimaAtualizacao()));
        }
    }

   /* public List<DadosMotoboyList> getListaLocalizacao() {
        List<DadosMotoboyList> lista = new ArrayList<>();
        listaLocalizacao.forEach(item->{
            if(item != null&& item.localizacao()!= null){
                lista.add(new DadosMotoboyList(item));
            }
        });
        return lista;
    }*/


}

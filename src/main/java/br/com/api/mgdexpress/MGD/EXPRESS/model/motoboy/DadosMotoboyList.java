package br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public record DadosMotoboyList(Long id, String nome, Localizacao localizacao, Boolean disponivel, String emailGerente,
                               LocalDateTime ultimaAtualizacao) {
    public DadosMotoboyList(Motoboy motoboy) {
        this(motoboy.getId(), motoboy.getNome(), motoboy.getLocalizacao(),motoboy.getDisponivel(),motoboy.getEmailGerente(),null);
    }

    public DadosMotoboyList(DadosMotoboyList motoboy) {
        this(motoboy.id, motoboy.nome, motoboy.localizacao(),motoboy.disponivel(),motoboy.emailGerente(),motoboy.ultimaAtualizacao);
    }
}

package br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy;

import br.com.api.mgdexpress.MGD.EXPRESS.model.historico.Historico;
import jakarta.persistence.Embedded;
import jakarta.persistence.OneToOne;

public record DtoMotoboyDadosCompleto(
         Long id,
         String nome,
         String telefone,
         String email,
         String cpf,
         String nomeParente,
         String telefoneEmergencia,
         String chavePix,
         Localizacao localizacao,
         Historico histico,
         Boolean disponivel,
         Boolean ativo,
         String emailGerente,
         Double totalAReceberNoDia
) {
    public DtoMotoboyDadosCompleto(Motoboy motoboy, Double total) {
        this(motoboy.getId(), motoboy.getNome(), motoboy.getTelefone(), motoboy.getEmail(), motoboy.getCpf(), motoboy.getNomeParente(), motoboy.getTelefoneEmergencia(), motoboy.getChavePix(), (motoboy.getLocalizacao()== null)?null:motoboy.getLocalizacao() ,motoboy.getHistico(),motoboy.getDisponivel(),motoboy.getAtivo(), motoboy.getEmailGerente(), total);
    }
}

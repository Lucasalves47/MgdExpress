package br.com.api.mgdexpress.MGD.EXPRESS.model.gerente;

import br.com.api.mgdexpress.MGD.EXPRESS.model.gerenteTemporario.GerenteTemporario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosGerente(
        Long id,
        @NotBlank
        String nome,
        @NotBlank
        String telefone,
        @Email @NotBlank
        String email,
        @NotBlank
        String senha,
        @NotBlank
        String nomeEstabelecimento,
        @NotBlank
        String localEstabelecimento
       ) {
    public DadosGerente(Gerente gerente) {
        this(gerente.getId(),gerente.getNome(), gerente.getTelefone(), gerente.getEmail(), gerente.getSenha(),gerente.getNomeEstebelecimento(), gerente.getLocalEstabelecimento());
    }

    public DadosGerente(GerenteTemporario gerenteTemporario) {
        this(gerenteTemporario.getId(),gerenteTemporario.getNome(),gerenteTemporario.getTelefone(),gerenteTemporario.getEmail(),null,gerenteTemporario.getNomeEstebelecimento(),gerenteTemporario.getLocalEstabelecimento());
    }
}

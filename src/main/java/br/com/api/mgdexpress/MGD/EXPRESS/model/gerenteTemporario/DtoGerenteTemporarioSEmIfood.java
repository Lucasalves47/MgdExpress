package br.com.api.mgdexpress.MGD.EXPRESS.model.gerenteTemporario;

import br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy.Localizacao;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DtoGerenteTemporarioSEmIfood(
        Long id,
        @NotBlank
        String nome,
        @NotBlank
        String nomeEstabelecimento,
        @NotBlank
        String telefone,
        @Email @NotBlank
        String email,
        @NotBlank
        String senha,
        @NotBlank
        String localEstabelecimento,
        @NotBlank
        String lat,
        @NotBlank
        String lng,

        @NotBlank
        String cnpj,

        @NotBlank
        String merchatId


) {
}

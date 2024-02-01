package br.com.api.mgdexpress.MGD.EXPRESS.model.gerente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DtoGerenteTemporario(
        @NotBlank
        String telefone,
        @Email @NotBlank
        String email,
        @NotBlank
        String senha,
        @NotBlank
        String clientId,
        @NotBlank
        String clientSecret
) {
}

package br.com.api.mgdexpress.MGD.EXPRESS.model.pedido;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosPedido(
        Long id,
        @NotBlank
        String nomePedido,
        @NotBlank
        String nomeEstabelecimento,
        @NotBlank
        String localOrigem,
        @NotBlank
        String localDestino,
        @NotNull
        BigDecimal valor,

        String observacao,
        @NotBlank
        String itensDoPedido,
        Status status,
        LocalDate dataCriacao,
        @NotNull @Valid
        Long GerenteId) {
        public DadosPedido(Pedido pedido) {
                this(pedido.getId(),pedido.getNomePedido(), pedido.getNomeEstabelecimento(), pedido.getLocalOrigem(), pedido.getLocalDestino(),pedido.getValor(), pedido.getObservacao(), pedido.getItensDoPedido(), pedido.getStatus(),pedido.getDataCriacao(),pedido.getGerente().getId());
        }
}

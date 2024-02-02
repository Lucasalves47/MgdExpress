package br.com.api.mgdexpress.MGD.EXPRESS.model.pedido;

import br.com.api.mgdexpress.MGD.EXPRESS.model.gerente.DadosGerente;
import br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy.DadosMotoboy;
import br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy.Localizacao;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosPedidoCompleto(
        Long id,
        @NotBlank
        String nomeEstabelecimento,
        @NotBlank
        String localOrigem,
        @NotBlank
        String localDestino,
        @NotNull
        BigDecimal valorPedido,

        String metodoPagamento,
        @NotBlank
        String itensDoPedido,
        Status status,
        LocalDate dataCriacao,
        @NotNull @Valid
        DadosGerente dadosGerente,
        DadosMotoboy dadosMotoboy,
        @NotNull
        Localizacao localizacao,
        Double troco,
        Double taxaEntrega) {
    public DadosPedidoCompleto(Pedido pedido) {
        this(pedido.getId(), pedido.getNomeEstabelecimento(), pedido.getLocalOrigem(), pedido.getLocalDestino(), pedido.getValorDoPedido(), pedido.getMetodoPagamento(), pedido.getItensDoPedido(), pedido.getStatus(), pedido.getDataCriacao(), new DadosGerente(pedido.getGerente()), new DadosMotoboy(pedido.getMotoboy()),pedido.getLocalizacao(), pedido.getTroco(), pedido.getTaxaEntrega());
    }
}


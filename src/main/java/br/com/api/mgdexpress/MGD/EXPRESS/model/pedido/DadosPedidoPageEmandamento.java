package br.com.api.mgdexpress.MGD.EXPRESS.model.pedido;

import java.math.BigDecimal;

public record DadosPedidoPageEmandamento(Long id, String nomePedido, BigDecimal valorPedido,Double taxaEntrega, String localDestino) {
    public DadosPedidoPageEmandamento(Pedido pedido) {
        this(pedido.getId(), pedido.getNomePedido(), pedido.getValorDoPedido(), pedido.getTaxaEntrega(), pedido.getLocalDestino());
    }
}

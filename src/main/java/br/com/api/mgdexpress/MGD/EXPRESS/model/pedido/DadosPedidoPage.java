package br.com.api.mgdexpress.MGD.EXPRESS.model.pedido;

import br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy.Localizacao;

import java.math.BigDecimal;
import java.util.List;

public record DadosPedidoPage(Long id, String nomePedido, String nomeEstabelecimento, BigDecimal valorPedido, Double taxaEntrega, String localDestino, Status status,
                              Localizacao localizacaoEntrega) {
    public DadosPedidoPage(Pedido pedido) {
        this(pedido.getId(),pedido.getNomePedido(), pedido.getNomeEstabelecimento(), pedido.getValorDoPedido(), pedido.getTaxaEntrega(), pedido.getLocalDestino(),pedido.getStatus(),pedido.getLocalizacao());
    }


}

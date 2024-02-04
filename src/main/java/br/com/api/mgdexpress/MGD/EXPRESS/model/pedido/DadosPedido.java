package br.com.api.mgdexpress.MGD.EXPRESS.model.pedido;

import br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy.Localizacao;
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
        String localDestino,
        @NotNull
        BigDecimal valorPedido,

        String metodoPagamento,
        @NotBlank
        String itensDoPedido,

        Localizacao localizacao,
        String lat,
        String lng,

        @NotNull
        Double troco,
        Double taxaEntrega
        ) {
        public DadosPedido(Pedido pedido) {
                this(pedido.getId(),pedido.getNomePedido(), pedido.getLocalDestino(),pedido.getValorDoPedido(), pedido.getMetodoPagamento(), pedido.getItensDoPedido(),pedido.getLocalizacao(),pedido.getLocalizacao().getLatitude(),pedido.getLocalizacao().getLongitude()
                        , pedido.getTroco(), pedido.getTaxaEntrega());
        }
}

package br.com.api.mgdexpress.MGD.EXPRESS.model.historico;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosHistoricoListMotoboy(Long id, LocalDate dataEntrega, String nomeStabelecimento, BigDecimal valor) {

    public DadosHistoricoListMotoboy (Historico historico){
        this(historico.getId(), historico.getDataEntrega(),historico.getNomeEstabelecimento(),historico.getValorPedido());
    }
}

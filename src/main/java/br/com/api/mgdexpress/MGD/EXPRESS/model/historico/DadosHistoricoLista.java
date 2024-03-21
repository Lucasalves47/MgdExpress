package br.com.api.mgdexpress.MGD.EXPRESS.model.historico;

import br.com.api.mgdexpress.MGD.EXPRESS.model.historico.Historico;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosHistoricoLista(Long id, LocalDate dataEntrega, String motoboyNome, String nomeStabelecimento, BigDecimal valor) {

    public DadosHistoricoLista (Historico historico){
        this(historico.getId(), historico.getDataEntrega(), historico.getMotoboy().getNome(),historico.getNomeEstabelecimento(),historico.getValorDoPedido());
    }
}

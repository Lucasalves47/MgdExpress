package br.com.api.mgdexpress.MGD.EXPRESS.model.historico;

import java.math.BigDecimal;

public record DadosListaHistoricoEntregasDoDia(String nomeMotoboy, BigDecimal valor,Integer entregas,Double km) {

    public DadosListaHistoricoEntregasDoDia(Historico historico,Integer entregas) {
        this(historico.getMotoboy().getNome(),historico.getValor(),entregas, historico.getDistancia());
    }
}

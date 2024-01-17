package br.com.api.mgdexpress.MGD.EXPRESS.model.historico;

import br.com.api.mgdexpress.MGD.EXPRESS.controller.DtoEntregaDistancia;

import java.math.BigDecimal;

public record DadosListaHistoricoEntregasDoDia(Long id,String nomeMotoboy, BigDecimal valor,Integer entregas,Double km) {

    public DadosListaHistoricoEntregasDoDia(Historico historico, DtoEntregaDistancia entregas) {
        this(historico.getId(),historico.getMotoboy().getNome(),historico.getValor(),entregas.entregas(), entregas.km());
    }
}

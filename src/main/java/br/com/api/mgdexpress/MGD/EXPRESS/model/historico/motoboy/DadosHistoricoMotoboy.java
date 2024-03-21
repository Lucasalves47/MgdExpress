package br.com.api.mgdexpress.MGD.EXPRESS.model.historico.motoboy;

import br.com.api.mgdexpress.MGD.EXPRESS.model.gerente.Gerente;
import br.com.api.mgdexpress.MGD.EXPRESS.model.historico.Historico;
import br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy.Localizacao;
import br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy.Motoboy;
import br.com.api.mgdexpress.MGD.EXPRESS.model.pedido.Pedido;
import br.com.api.mgdexpress.MGD.EXPRESS.model.pedido.Status;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record DadosHistoricoMotoboy(
        Long id,
        String LocalDestino,
        BigDecimal valor,
        String observacao,
        String itensDoPedido,
        LocalDate dataCriacao,
        LocalDate dataEntrega,
        Long idGerente,
        String nome,
        String telefone,
        String email,
        String nomeEstebelecimento,
        String localEstabelecimento
) {

    public DadosHistoricoMotoboy(Historico historico) {
        this(historico.getId(), historico.getLocalDestino(),
                historico.getValorDoPedido(), historico.getMetodoPagamento(),
                historico.getItensDoPedido(), historico.getDataCriacao(),
                historico.getDataEntrega(),historico.getGerente().getId(),
                historico.getGerente().getNome(),historico.getGerente().getTelefone(),
                historico.getGerente().getEmail(),
                historico.getGerente().getNomeEstebelecimento(),
                historico.getGerente().getLocalEstabelecimento());
    }
}

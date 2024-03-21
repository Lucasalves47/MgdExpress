package br.com.api.mgdexpress.MGD.EXPRESS.model.historico;

import br.com.api.mgdexpress.MGD.EXPRESS.model.gerente.Gerente;
import br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy.Localizacao;
import br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy.Motoboy;
import br.com.api.mgdexpress.MGD.EXPRESS.model.pedido.Pedido;
import br.com.api.mgdexpress.MGD.EXPRESS.model.pedido.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "historico")
public class Historico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String idPedidoIfood;
    private Long pedidoId;
    private String nomeEstabelecimento;
    private String nomePedido;
    private String nomeCliente;
    private String localOrigem;
    private String localDestino;
    private BigDecimal valorDoPedido;
    private Double taxaEntrega;
    private String metodoPagamento;
    private Double troco;
    private String itensDoPedido;
    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDate dataCriacao;
    private LocalDate dataEntrega;
    private Localizacao localizacao;
    private String latitudeLoja;
    private String longitudeLoja;
    @ManyToOne
    private Motoboy motoboy;
    @ManyToOne
    private Gerente gerente;
    private Double distancia;

    private String tipoDeEntrega;
    private String pedidoAgendadoStart;
    private String pedidoAgendadoEnd;
    private String orderType;
    private String card;
    private String beneficios;
    private String observacao;


    public Historico(Pedido pedido) {

        this.idPedidoIfood = pedido.getIdPedidoIfood();
        this.pedidoId = pedido.getId();

        this.nomePedido = pedido.getNomePedido();
        this.nomeCliente = pedido.getNomeCliente();


        this.dataEntrega = pedido.getDataEntrega();
        this.localizacao = pedido.getLocalizacao();
        this.latitudeLoja = pedido.getLatitudeLoja();
        this.longitudeLoja = pedido.getLongitudeLoja();
        this.motoboy =pedido.getMotoboy();
        this.gerente = pedido.getGerente();

        this.distancia = pedido.getDistancia();
        this.tipoDeEntrega = pedido.getTipoDeEntrega();
        this.pedidoAgendadoStart = pedido.getPedidoAgendadoStart();
        this.pedidoAgendadoEnd = pedido.getPedidoAgendadoEnd();
        this.orderType = pedido.getOrderType();
        this.card = pedido.getCard();
        this.beneficios = pedido.getBeneficios();
        this.observacao = pedido.getObservacao();

        this.nomeEstabelecimento = pedido.getNomeEstabelecimento();
        this.localOrigem = pedido.getLocalOrigem();
        this.localDestino =pedido.getLocalDestino();
        this.valorDoPedido = pedido.getValorDoPedido();
        this.troco = pedido.getTroco();
        this.taxaEntrega = pedido.getTaxaEntrega();
        this.metodoPagamento = pedido.getMetodoPagamento();
        this.itensDoPedido = pedido.getItensDoPedido();
        this.status = Status.FINALIZADO;
        this.dataCriacao = pedido.getDataCriacao();
        this.dataEntrega = LocalDate.now();
        this.motoboy = pedido.getMotoboy();
        this.gerente = pedido.getGerente();
        this.pedidoId = pedido.getId();
        this.distancia = pedido.getDistancia();
        this.localizacao = pedido.getLocalizacao();
    }
}

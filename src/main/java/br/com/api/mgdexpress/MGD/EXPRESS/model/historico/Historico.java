package br.com.api.mgdexpress.MGD.EXPRESS.model.historico;

import br.com.api.mgdexpress.MGD.EXPRESS.model.gerente.Gerente;
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
    private Long pedidoId;
    private String nomeEstabelecimento;
    private String localOrigem;
    private String LocalDestino;
    private BigDecimal valor;
    private String observacao;
    private String itensDoPedido;
    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDate dataCriacao;
    private LocalDate dataEntrega;
    @ManyToOne
    private Motoboy motoboy;
    @ManyToOne
    private Gerente gerente;
    private Double distancia;



    public Historico(Pedido pedido) {
        this.nomeEstabelecimento = pedido.getNomeEstabelecimento();
        this.localOrigem = pedido.getLocalOrigem();
        LocalDestino =pedido.getLocalDestino();
        this.valor = pedido.getValor();
        this.observacao = pedido.getObservacao();
        this.itensDoPedido = pedido.getItensDoPedido();
        this.status = Status.FINALIZADO;
        this.dataCriacao = pedido.getDataCriacao();
        this.dataEntrega = LocalDate.now();
        this.motoboy = pedido.getMotoboy();
        this.gerente = pedido.getGerente();
        this.pedidoId = pedido.getId();
        this.distancia = pedido.getDistancia();
    }
}

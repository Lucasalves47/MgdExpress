package br.com.api.mgdexpress.MGD.EXPRESS.model.pedido;

import br.com.api.mgdexpress.MGD.EXPRESS.model.gerente.Gerente;
import br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy.Motoboy;
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
@Table(name = "pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeEstabelecimento;
    private String nomePedido;
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

    public Pedido(DadosPedido dadosPedido, Gerente gerente) {

        this.nomeEstabelecimento = gerente.getNomeEstebelecimento();
        this.nomePedido = dadosPedido.nomePedido();
        this.localOrigem = gerente.getLocalEstabelecimento();
        this.LocalDestino = dadosPedido.localDestino();
        this.valor = dadosPedido.valor();
        this.observacao = dadosPedido.observacao();
        this.itensDoPedido = dadosPedido.itensDoPedido();
        this.status = Status.INICIAR;
        this.dataCriacao = LocalDate.now();
        this.gerente = gerente;
        this.distancia = null;

    }

}

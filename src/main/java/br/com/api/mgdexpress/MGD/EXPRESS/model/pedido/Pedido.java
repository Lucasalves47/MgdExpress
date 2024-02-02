package br.com.api.mgdexpress.MGD.EXPRESS.model.pedido;

import br.com.api.mgdexpress.MGD.EXPRESS.model.gerente.Gerente;
import br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy.Localizacao;
import br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy.Motoboy;
import br.com.api.mgdexpress.MGD.EXPRESS.service.requests.Requests;
import br.com.api.mgdexpress.MGD.EXPRESS.service.requests.gsonData.DtoDetalhesDoPedido;
import br.com.api.mgdexpress.MGD.EXPRESS.service.requests.gsonData.Item;
import br.com.api.mgdexpress.MGD.EXPRESS.service.requests.gsonData.Method;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

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
    private String idPedidoIfood;
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
    @ManyToOne
    private Motoboy motoboy;
    @ManyToOne
    private Gerente gerente;
    private Double distancia;

    public Pedido(DadosPedido dadosPedido, Gerente gerente) {

        this.nomeEstabelecimento = gerente.getNomeEstebelecimento();
        this.nomePedido = dadosPedido.nomePedido();
        this.localOrigem = gerente.getLocalEstabelecimento();
        this.localDestino = dadosPedido.localDestino();
        this.valorDoPedido = dadosPedido.valorPedido();
        this.metodoPagamento = dadosPedido.metodoPagamento();
        this.troco = dadosPedido.troco();
        this.localizacao = dadosPedido.localizacao();
        this.taxaEntrega = 8.0;
        this.itensDoPedido = dadosPedido.itensDoPedido();
        this.status = Status.INICIAR;
        this.dataCriacao = LocalDate.now();
        this.gerente = gerente;
        this.distancia = null;

    }

    public Pedido(DtoDetalhesDoPedido p,String token,Gerente gerente) {
        var endereco = new Requests().requestDadosGerente(token).getAddress();
        var entregar = p.getDelivery().getDeliveryAddress();
        StringBuilder itens = new StringBuilder();
        var cordenadas = p.getDelivery().getDeliveryAddress().getCoordinates();
        Double totalTroco = 0.0;
        for (Method method : p.getPayments().getMethods()) {
            totalTroco += (method.getCash()==null)? 0.0 :method.getCash().getChangeFor();
        }

        for (Item item :p.getItems()){
            itens.append(item.getName()).append(",\n");
        }


        this.nomeEstabelecimento = p.getMerchant().getName();
        this.nomeCliente = p.getCustomer().getName();
        this.nomePedido = p.getDisplayId();
        this.localOrigem =endereco.getCountry()+",  "+endereco.getState()+",  "+endereco.getCity()+",  "+endereco.getStreet()+",  "+endereco.getNumber()+",  "+endereco.getPostalCode();
        this.localDestino = entregar.getCountry()+",  "+entregar.getState()+",  "+entregar.getCity()+",  "+entregar.getFormattedAddress()+",  "+entregar.getPostalCode();
        this.valorDoPedido = new BigDecimal(p.getTotal().getOrderAmount()-p.getTotal().getDeliveryFee()+8);
        this.taxaEntrega = 8.0;
        p.getPayments().getMethods().forEach(m->this.metodoPagamento = (this.metodoPagamento== null)? m.getMethod():this.metodoPagamento+",\n"+m.getMethod());
        this.troco = totalTroco;
        this.itensDoPedido = itens.toString();
        this.status = Status.INICIAR;
        this.dataCriacao =LocalDate.now();
        this.localizacao = new Localizacao(cordenadas.getLatitude()+"",cordenadas.getLongitude()+"");
        this.gerente = gerente;
        this.distancia = null;
        this.idPedidoIfood = p.getId();

    }


}

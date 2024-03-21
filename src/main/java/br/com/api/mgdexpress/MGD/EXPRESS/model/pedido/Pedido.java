package br.com.api.mgdexpress.MGD.EXPRESS.model.pedido;

import br.com.api.mgdexpress.MGD.EXPRESS.model.gerente.Gerente;
import br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy.Localizacao;
import br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy.Motoboy;
import br.com.api.mgdexpress.MGD.EXPRESS.service.requests.Requests;
import br.com.api.mgdexpress.MGD.EXPRESS.service.requests.gsonData.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

    public Pedido(DadosPedido dadosPedido, Gerente gerente) {

        this.nomeEstabelecimento = gerente.getNomeEstebelecimento();
        this.nomePedido = dadosPedido.nomePedido();
        this.localOrigem = gerente.getLocalEstabelecimento();
        this.localDestino = dadosPedido.localDestino();
        this.valorDoPedido = dadosPedido.valorPedido();
        this.metodoPagamento = dadosPedido.metodoPagamento();
        this.troco = dadosPedido.troco();
        this.localizacao = new Localizacao(dadosPedido.lng(),dadosPedido.lat());
        this.taxaEntrega = 8.0;
        this.itensDoPedido = dadosPedido.itensDoPedido();
        this.status = Status.INICIAR;
        this.dataCriacao = LocalDate.now();
        this.gerente = gerente;
        this.distancia = null;

    }

    public Pedido(DtoDetalhesDoPedido p,String token,Gerente gerente) {
        var endereco = new Requests().requestDadosGerente(token,gerente.getNome(),gerente.getNomeEstebelecimento()).getAddress();
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
        this.longitudeLoja = gerente.getLocalizacao().getLongitude();
        this.latitudeLoja = gerente.getLocalizacao().getLatitude();

        //******* dados exigidos pelo ifood para a homologaçao


        var inicio = ((p.getSchedule() != null)?p.getSchedule().getDeliveryDateTimeStart() : LocalDateTime.now()+" nulo").split("T");
        var fim = ((p.getSchedule()!= null)?p.getSchedule().getDeliveryDateTimeEnd() : LocalDateTime.now()+" nulo").split("T");


        var datainicio = inicio[0].split("-");
        this.pedidoAgendadoStart = datainicio[2]+"-"+ datainicio[1]+"-"+ datainicio[0]+"  "+inicio[1].substring(0,5);


        var dataFinal = inicio[0].split("-");
        this.pedidoAgendadoEnd = dataFinal[2]+"-"+dataFinal[1]+"-"+dataFinal[0]+"  "+fim[1].substring(0,5);

        this.orderType = p.getOrderType();
         p.getPayments().getMethods().forEach(m->{
             this.card = (this.getCard() == null)? m.getCard().getBrand():this.getCard()+"</br>"+m.getCard().getBrand();
        });

        var beneficiosHtml = new StringBuilder();

        if(p.getBenefits()!=null) {
            beneficiosHtml.append("<h4>Beneficios</h4>");
            p.getBenefits().forEach(b -> {
                        b.getSponsorshipValues().forEach(beneficios -> {
                            beneficiosHtml.append("<p>responsável: ").append(beneficios.getName()).append("</p>");
                            beneficiosHtml.append("<p>valor: ").append(beneficios.getValue()).append("</p>");
                        });
                        beneficiosHtml.append("<p>valor total: ").append(b.getValue()).append("</p>");
                    }

            );
        }

        this.beneficios = beneficiosHtml.toString();

        var intesHtml = new StringBuilder();
        p.getItems().forEach(item->{
            intesHtml.append("<div class=\"itensIfood\">");
            intesHtml.append(" <p style=\"margin-left: 10px;\">").append(item.getQuantity()).append(item.getName()).append("</p>");
            intesHtml.append("<p>preço unitario: ").append(item.getUnit()).append("R$</p>");
            intesHtml.append("<p>Total: ").append(item.getTotalPrice()).append("R$</p>");
            intesHtml.append("<p>Obs: ").append((item.getObservations()!=null)?item.getObservations():"").append("</p>");
            intesHtml.append("</div>");
        });

        this.observacao = intesHtml.toString();

    }


    public Pedido(Gerente gerente,String comanda) {
        this.nomeEstabelecimento = gerente.getLocalEstabelecimento();
        this.localOrigem = gerente.getLocalEstabelecimento();
        this.status = Status.INICIAR;
        this.dataCriacao = LocalDate.now();
        this.latitudeLoja = gerente.getLocalizacao().getLatitude();
        this.longitudeLoja = gerente.getLocalizacao().getLongitude();
        this.gerente = gerente;
        this.nomePedido = comanda;
        this.idPedidoIfood = "";
        this.nomeCliente = "";
        this.localDestino = "";
        this.valorDoPedido = new BigDecimal("0.0");
        this.taxaEntrega = 8.0;
        this.metodoPagamento = "";
        this.troco = 0.0;
        this.itensDoPedido = "";
        this.localizacao = new Localizacao("-19.9167","-43.9333");
        this.distancia = null;
        this.tipoDeEntrega = "";
        this.pedidoAgendadoStart = "";
        this.pedidoAgendadoEnd = "";
        this.orderType = "";
        this.card = "";
        this.beneficios = "";
        this.observacao = "";

    }
}

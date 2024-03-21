package br.com.api.mgdexpress.MGD.EXPRESS.service.requests.gsonData;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DtoDetalhesDoPedido {
    private String id;
    private Delivery delivery;
    private String orderType;
    private String orderTiming;
    private String displayId;
    private String createdAt;
    private boolean isTest;
    private Merchant merchant;
    private Customer customer;
    private List<Item> items;
    private Total total;
    private Payments payments;
    private Schedule schedule;
    private List<Beneficios> benefits;
}





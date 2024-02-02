package br.com.api.mgdexpress.MGD.EXPRESS.service.requests.gsonData;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer {
    private String id;
    private String name;
    private String documentNumber;
    private Phone phone;
    private int ordersCountOnMerchant;
    private String segmentation;

    // Getters e Setters (omiti para brevidade)
}

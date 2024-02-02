package br.com.api.mgdexpress.MGD.EXPRESS.service.requests.gsonData;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item {
    private String name;
    private String unit;
    private int quantity;
    private double totalPrice;
}

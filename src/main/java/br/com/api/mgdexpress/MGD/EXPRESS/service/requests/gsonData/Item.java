package br.com.api.mgdexpress.MGD.EXPRESS.service.requests.gsonData;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Item {
    private String name;
    private String unit;
    private int quantity;
    private double totalPrice;
    private String observations;
}

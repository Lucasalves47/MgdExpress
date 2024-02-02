package br.com.api.mgdexpress.MGD.EXPRESS.service.requests.gsonData;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Delivery {
    private String mode;
    private String observations;
    private DeliveryAddress deliveryAddress;
    private String pickupCode;

    // Getters e Setters (omiti para brevidade)
}

package br.com.api.mgdexpress.MGD.EXPRESS.service.requests.gsonData;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryAddress {
    
    private String formattedAddress;
    private String neighborhood;
    private String complement;
    private String postalCode;
    private String city;
    private String state;
    private String country;
    private String reference;
    private Coordinates coordinates;

    // Getters e Setters (omiti para brevidade)
}

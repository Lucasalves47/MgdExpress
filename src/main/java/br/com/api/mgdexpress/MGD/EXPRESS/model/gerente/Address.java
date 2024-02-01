package br.com.api.mgdexpress.MGD.EXPRESS.model.gerente;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private String country;
    private String state;
    private String city;
    private String district;
    private String street;
    private String number;
    private String postalCode;
    private double latitude;
    private double longitude;


}

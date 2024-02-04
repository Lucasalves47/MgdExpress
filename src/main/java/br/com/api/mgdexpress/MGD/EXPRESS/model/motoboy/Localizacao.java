package br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy;

import jakarta.persistence.Column;
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
public class Localizacao {

    private String longitude;
    private String latitude;
}

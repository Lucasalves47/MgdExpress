package br.com.api.mgdexpress.MGD.EXPRESS.model.handshake_disput;

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
public class Amount {
    private String value;
    private String currency;
}

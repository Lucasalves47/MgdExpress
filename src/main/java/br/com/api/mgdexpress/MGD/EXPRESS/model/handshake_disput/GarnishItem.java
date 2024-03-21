package br.com.api.mgdexpress.MGD.EXPRESS.model.handshake_disput;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class GarnishItem {
    private String id;
    private String parentUniqueId;
    private String integrationId;
    private int quantity;
    private int index;
    @Embedded
    private Amount amount;
    private String reason;
}

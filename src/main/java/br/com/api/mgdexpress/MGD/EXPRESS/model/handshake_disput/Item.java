package br.com.api.mgdexpress.MGD.EXPRESS.model.handshake_disput;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item {
    private String id;
    private String uniqueId;
    private String integrationId;
    private int index;
    private int quantity;
    private Amount amount;
    private String reason;
}

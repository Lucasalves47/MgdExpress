package br.com.api.mgdexpress.MGD.EXPRESS.model.handshake_disput;


import br.com.api.mgdexpress.MGD.EXPRESS.service.requests.gsonData.Item;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Metadata {
    private String disputeId;
    private String action;
    private String timeoutAction;
    private String handshakeType;
    private String handshakeGroup;
    private String message;
    private String expiresAt;
    @ElementCollection
    @Embedded
    private List<Alternative> alternatives;

    @Column(name = "createdData")
    private String createdAt;
    @Embedded
    private SubMetadata metadata;
}

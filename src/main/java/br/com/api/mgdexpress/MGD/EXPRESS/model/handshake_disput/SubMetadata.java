package br.com.api.mgdexpress.MGD.EXPRESS.model.handshake_disput;

import br.com.api.mgdexpress.MGD.EXPRESS.service.requests.gsonData.Item;
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
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class SubMetadata {

    @ElementCollection
    @Embedded
    private List<Evidence> evidences;
    @ElementCollection
    @Embedded
    private List<Item> items;
    @ElementCollection
    @Embedded
    private List<GarnishItem> garnishItems;
}

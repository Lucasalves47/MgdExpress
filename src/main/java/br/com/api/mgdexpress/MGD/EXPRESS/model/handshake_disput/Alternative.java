package br.com.api.mgdexpress.MGD.EXPRESS.model.handshake_disput;


import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Alternative {
    private String id;
    private String type;
    @Embedded
    private AlternativeMetaData metadata;
}

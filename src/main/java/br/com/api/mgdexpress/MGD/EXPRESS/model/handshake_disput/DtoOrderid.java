package br.com.api.mgdexpress.MGD.EXPRESS.model.handshake_disput;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table
public class DtoOrderid {
    @Id
    private String id;
    private String orderId;
    private String code;
    private String fullcode;
    private String merchantId;
    private String createdAt;
    @Embedded
    private Metadata metadata;
    private String receivedAt;
}

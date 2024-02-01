package br.com.api.mgdexpress.MGD.EXPRESS.service.requests.gsonData;

import br.com.api.mgdexpress.MGD.EXPRESS.model.gerente.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DtoDadosGerente {

    private String id;
    private String name;
    private String corporateName;
    private int averageTicket;
    private boolean exclusive;
    private String type;
    private String status;
    private String createdAt;
    private Address address;


}



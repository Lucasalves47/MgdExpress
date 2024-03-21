package br.com.api.mgdexpress.MGD.EXPRESS.service.requests.gsonData;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Beneficios {
    private List<SponsorshipValues> sponsorshipValues;
    private Double value;
}

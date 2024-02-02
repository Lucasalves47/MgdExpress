package br.com.api.mgdexpress.MGD.EXPRESS.service.requests.gsonData;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Method {
    private double value;
    private String method;
    private String type;
    private Card card;
    private Cash cash;
}

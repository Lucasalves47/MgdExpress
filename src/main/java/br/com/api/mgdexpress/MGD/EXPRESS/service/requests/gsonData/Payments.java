package br.com.api.mgdexpress.MGD.EXPRESS.service.requests.gsonData;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Payments {
    private double pending;
    private List<Method> methods;


}

package br.com.api.mgdexpress.MGD.EXPRESS.service.requests.gsonData;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModelTokenResponse {

    private String accessToken;
    private String type;
    private String expiresIn;
}

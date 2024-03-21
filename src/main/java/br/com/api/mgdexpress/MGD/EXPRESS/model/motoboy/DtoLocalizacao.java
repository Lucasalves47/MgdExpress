package br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class DtoLocalizacao extends Localizacao{
    private Long id;
    private LocalDateTime ultimaEntrega;

    public DtoLocalizacao(Motoboy motoboy) {
        this.id = motoboy.getId();
        this.setLatitude(motoboy.getLocalizacao().getLatitude());
        this.setLongitude(motoboy.getLocalizacao().getLongitude());
        this.ultimaEntrega = motoboy.getUltimaEntrega();

    }
}

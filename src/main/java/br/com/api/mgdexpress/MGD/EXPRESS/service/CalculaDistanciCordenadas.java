package br.com.api.mgdexpress.MGD.EXPRESS.service;

import br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy.DtoId;
import br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy.DtoLocalizacao;
import br.com.api.mgdexpress.MGD.EXPRESS.model.motoboy.Localizacao;

import java.lang.Math;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class CalculaDistanciCordenadas {
        public static DtoId menorDistancia(Localizacao loja , List<DtoLocalizacao> coordenadas) {

            double menorDistancia = Double.POSITIVE_INFINITY;
            DtoLocalizacao coordenadaMaisProxima = new DtoLocalizacao(null,LocalDateTime.now());

            for (DtoLocalizacao coordenada : coordenadas) {
                double distancia = calcularDistancia(Double.parseDouble(loja.getLatitude()),Double.parseDouble(loja.getLongitude()), Double.parseDouble(coordenada.getLatitude()),Double.parseDouble(coordenada.getLongitude()));
                var agora = LocalDateTime.now();
                var duracao1 = Duration.between(coordenada.getUltimaEntrega(),agora);
                var duracao2 = Duration.between(coordenadaMaisProxima.getUltimaEntrega(),agora);

                if (distancia < menorDistancia && duracao1.compareTo(duracao2) > 0) {
                    menorDistancia = distancia;
                    coordenadaMaisProxima = coordenada;
                }
            }
            return  new DtoId(coordenadaMaisProxima.getId());
        }

        private static double calcularDistancia(double lat1, double lon1, double lat2, double lon2) {
            final double R = 6371; // raio da Terra em quilômetros
            double dLat = Math.toRadians(lat2 - lat1);
            double dLon = Math.toRadians(lon2 - lon1);
            double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                    + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                    * Math.sin(dLon / 2) * Math.sin(dLon / 2);
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
            return R * c;

        }
    }



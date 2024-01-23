package br.com.api.mgdexpress.MGD.EXPRESS.controller;

import java.math.BigDecimal;

public record DtoEntregaDistancia(Integer entregas, Double km, BigDecimal valor) {
}

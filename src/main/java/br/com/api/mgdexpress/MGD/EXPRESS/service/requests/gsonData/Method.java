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

    public String getMethod() {

        return switch (method) {
            case "CASH" -> "DINHEIRO";
            case "CREDIT" -> "CRÉDITO";
            case "DEBIT" -> "DÉBITO";
            case "MEAL_VOUCHER"->"VALE REFEIÇÃO";
            case "FOOD_VOUCHER"->"VALE ALIMENTAÇÃO";
            case "GIFT_CARD"->"VALE-PRESENTE";
            case "DIGITAL_WALLE"->"CARTEIRA DIGITA";
            case "PIX"->"PIX";
            default -> "não especificado";
        };

    }
}



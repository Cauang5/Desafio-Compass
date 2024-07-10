package org.compass.desafio.enums;

public enum UnityOfMeasure {

    KILO("kilos"),
    GRAMA("gramas"),
    LITRO("litros"),
    MILILITRO("mililitros");

    private final String unidadeDeMedida;

    UnityOfMeasure(String unidadeMedida) {
        unidadeDeMedida = unidadeMedida;
    }

    public static UnityOfMeasure fromUnidadeDeMedida(String unidadeDeMedida) {
        for (UnityOfMeasure medidaConvertida : values()) {
            if (medidaConvertida.unidadeDeMedida.equalsIgnoreCase(unidadeDeMedida)) {
                return medidaConvertida;
            }
        }
        throw new IllegalArgumentException("Invalid OrderStatus code");
    }
}
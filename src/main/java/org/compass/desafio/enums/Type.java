package org.compass.desafio.enums;

public enum Type {

    SABONETE("sabonete"),
    ESCOVA_DE_DENTES("escova de dente"),
    PASTA_DE_DENTES("pasta de dente"),
    ABSORVENTE("absorvente");

    private final String type;

    Type(String type) {
        this.type = type;
    }

    public static Type fromTipo(String type){
        for(Type tipo : values()){
            if (tipo.type.equalsIgnoreCase(type)){
                return tipo;
            }
        }
        throw new IllegalArgumentException("Invalid OrderStatus code");
    }
}

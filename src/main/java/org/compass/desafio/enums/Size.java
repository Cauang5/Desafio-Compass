package org.compass.desafio.enums;

public enum Size {
    INFANTIL("Infantil"),
    PP("PP"),
    P("P"),
    M("M"),
    G("G"),
    GG("GG");

    private final String tamanho;

    Size(String tamanho) {
        this.tamanho = tamanho;
    }

    public static Size fromTamanho(String tamanho){
        for (Size size : values()){
            if (size.tamanho.equalsIgnoreCase(tamanho)){
                return size;
            }
        }
        throw new IllegalArgumentException("Invalid OrderStatus code");
    }
}
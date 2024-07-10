package org.compass.desafio.enums;

public enum Gender {

    MASCULINO("M"),
    FEMININO("F");

    private final String sigla;

    Gender(String sigla) {
        this.sigla = sigla;
    }

    //Método estático para converter a sigla no valor
    public static Gender fromSigla(String sigla) {
        for (Gender value : Gender.values()) {
            if (value.sigla.equalsIgnoreCase(sigla)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid OrderStatus code");
    }
}
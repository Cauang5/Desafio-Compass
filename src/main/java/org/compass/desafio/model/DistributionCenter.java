package org.compass.desafio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class DistributionCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String neighborhood;
    private String city;
    private String state;
    private String cep;

    private int ClothingStock;
    private int FoodStock;
    private int HygieneProductStock;

    @Override
    public String toString() {
        return "Centro de Distribuição " +name+ ". "
                +address+ " - "
                +neighborhood+ ", "
                +city+ ", "
                +cep;
    }
}

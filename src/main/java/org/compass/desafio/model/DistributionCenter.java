package org.compass.desafio.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "distribution_center")
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

    private int ClothingStock = 1000;
    private int FoodStock = 1000;
    private int HygieneProductStock = 1000;

    @OneToMany(mappedBy = "distributionCenter")
    private List<Item> items;

    @Override
    public String toString() {
        return "Centro de Distribuição " +name+ ". "
                +address+ " - "
                +neighborhood+ ", "
                +city+ ", "
                +cep;
    }
}

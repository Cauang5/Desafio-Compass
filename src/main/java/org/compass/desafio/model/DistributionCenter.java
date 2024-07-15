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

    private int ClothingStock;
    private int FoodStock;
    private int HygieneProductStock;

    @OneToMany(mappedBy = "distributionCenter", fetch = FetchType.EAGER)
    private List<Clothing> clothing;

    @OneToMany(mappedBy = "distributionCenter", fetch = FetchType.EAGER)
    private List<Food> foods;

    @OneToMany(mappedBy = "distributionCenter", fetch = FetchType.EAGER)
    private List<HygieneProduct> hygieneProducts;

    @Override
    public String toString() {
        return "Centro de Distribuição " +name+ ". "
                +address+ " - "
                +neighborhood+ ", "
                +city+ ", "
                +cep;
    }
}

package org.compass.desafio.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shelter")
@Data
@NoArgsConstructor
public class Shelter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String shelterManager;
    private String phone;
    private String email;
    private int capacity;
    private int occupancy;
    private int clothingStock;
    private int foodStock;
    private int hygieneProductStock;

    @OneToMany(mappedBy = "shelter", fetch = FetchType.EAGER)
    private List<Clothing> clothing;

    @OneToMany(mappedBy = "shelter", fetch = FetchType.EAGER)
    private List<Food> foods;

    @OneToMany(mappedBy = "shelter", fetch = FetchType.EAGER)
    private List<HygieneProduct> hygieneProducts;

    @Override
    public String toString() {
        return "Shelter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", shelterManager='" + shelterManager + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", capacity=" + capacity +
                ", occupancy=" + occupancy + "% "+
                ", clothingStock=" + clothingStock +
                ", foodStock=" + foodStock +
                ", hygieneProductStock=" + hygieneProductStock +
                '}';
    }
}

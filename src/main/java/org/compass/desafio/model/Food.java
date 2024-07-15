package org.compass.desafio.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Entity
@Table(name = "food_item")
@Data
@EqualsAndHashCode(callSuper = true)
public class Food extends Item{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String unitOfMeasure;
    private LocalDate expirationDate;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "dc_id")
    private DistributionCenter distributionCenter;

    @ManyToOne
    @JoinColumn(name = "shelter")
    private Shelter shelter;
}

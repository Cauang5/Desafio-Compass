package org.compass.desafio.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "hygiene_product")
@Data
@EqualsAndHashCode(callSuper = true)
public class HygieneProduct extends Item{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;

    @ManyToOne
    @JoinColumn(name = "dc_id")
    private DistributionCenter distributionCenter;

    @ManyToOne
    @JoinColumn(name = "shelter")
    private Shelter shelter;
}

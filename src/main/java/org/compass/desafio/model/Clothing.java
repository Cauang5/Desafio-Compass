package org.compass.desafio.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "clothing_item")
@Data
@EqualsAndHashCode(callSuper = true)
public class Clothing extends Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String gender;
    private String size;

    @ManyToOne
    @JoinColumn(name = "dc_id")
    private DistributionCenter distributionCenter;
}

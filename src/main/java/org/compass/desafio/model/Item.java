package org.compass.desafio.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "item")
@Inheritance(strategy = InheritanceType.JOINED)
//Entidade super, que definirá o Id e a descrição dos itens
public class Item { // A classe Item é abstrata para não ser instanciada o objeto item.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @ManyToOne
    @JoinColumn(name = "dc_id")
    private DistributionCenter distributionCenter;

    @ManyToOne
    @JoinColumn(name = "shelter")
    private Shelter shelter;

}
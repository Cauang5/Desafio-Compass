package org.compass.desafio.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
//Entidade super, que definirá o Id e a descrição dos itens
public abstract class Item { // A classe Item é abstrata para não ser instanciada o objeto item.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) // Define a obrigatoriadade da coluna não ser nula
    private String description;
}
package com.ops.admin.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "CHARACTERISTIC_VALUE")
public class CharacteristicValue {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "VALUE")
    private String value;

    @ManyToOne
    @JoinColumn(name = "CHARACTERISTIC_ID")
    private Characteristic characteristic;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;
}

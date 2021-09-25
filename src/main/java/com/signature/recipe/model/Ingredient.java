package com.signature.recipe.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @EqualsAndHashCode.Include
    private String description;

    private BigDecimal amount;

    @OneToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure unit;

    @ManyToOne
    private Recipe recipe;

    public Ingredient(BigDecimal amount, UnitOfMeasure unit, String description) {
        this.amount = amount;
        this.unit = unit;
        this.description = description;
    }

    public Ingredient(BigDecimal amount, UnitOfMeasure unit, String description, Recipe recipe) {
        this.amount = amount;
        this.unit = unit;
        this.description = description;
        this.recipe = recipe;
    }
}
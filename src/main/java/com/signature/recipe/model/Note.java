package com.signature.recipe.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Lob
    private String description;

    @OneToOne
    private Recipe recipe;

    public Note(String description) {
        this.description = description;
    }

    public Note(String description, Recipe recipe) {
        this.description = description;
        this.recipe = recipe;
    }
}
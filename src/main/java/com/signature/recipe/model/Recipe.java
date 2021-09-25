package com.signature.recipe.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include()
    private Long id;
    private String description;

    @Lob
    private Byte[] image;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private Integer rating;

    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    @ManyToMany
    @JoinTable(
            name = "RECIPE_CATEGORY",
            joinColumns = @JoinColumn(name = "RECIPE_ID"),
            inverseJoinColumns = @JoinColumn(name = "CATEGORY_ID")
    )
    private Set<Category> categories;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients;
    private String directions;

    @OneToOne(cascade = CascadeType.ALL)
    private Note note;

    public Recipe() {
        this.categories = new HashSet<>();
        this.ingredients = new HashSet<>();
    }

    public Recipe addCategory(Category category) {
        category.getRecipes().add(this);
        this.categories.add(category);
        return this;
    }

    public Recipe addIngredients(Ingredient ingredient) {
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }

    public void setNote(Note note) {
        this.note = note;
        note.setRecipe(this);
    }
}
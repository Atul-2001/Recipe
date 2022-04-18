package com.signature.recipe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.signature.recipe.data.IngredientDTO;
import com.signature.recipe.data.RecipeDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @ToString.Exclude
    @Builder.Default
    private Set<Category> categories = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    @ToString.Exclude
    @Builder.Default
    private Set<Ingredient> ingredients = new HashSet<>();
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

    public Recipe addIngredient(Ingredient ingredient) {
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }

    public void setNote(Note note) {
        this.note = note;
        note.setRecipe(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Recipe recipe = (Recipe) o;
        return id != null && Objects.equals(id, recipe.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @JsonIgnore
    public RecipeDTO getDTO() {
        final RecipeDTO recipeDTO = new RecipeDTO();
        recipeDTO.setId(id);
        recipeDTO.setUrl(url);
        recipeDTO.setSource(source);
        recipeDTO.setPrepTime(prepTime);
        recipeDTO.setCookTime(cookTime);
        recipeDTO.setServings(servings);
        recipeDTO.setDifficulty(difficulty);
        recipeDTO.setDirections(directions);
        recipeDTO.setDescription(description);
        recipeDTO.setNotes(note == null ? null : note.getDTO());
        recipeDTO.setCategories(categories.stream().map(Category::getDTO)
                .collect(Collectors.toSet()));
        recipeDTO.setIngredients(ingredients.stream().map(Ingredient::getDTO)
                .sorted(Comparator.comparingLong(IngredientDTO::getId))
                .collect(Collectors.toCollection(LinkedHashSet::new)));
        return recipeDTO;
    }
}
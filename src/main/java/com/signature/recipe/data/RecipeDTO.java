package com.signature.recipe.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.signature.recipe.model.Difficulty;
import com.signature.recipe.model.Recipe;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDTO {

  @Id
  private Long id;
  private String url;
  private String source;
  private NoteDTO notes;
  private Integer prepTime;
  private Integer cookTime;
  private Integer servings;
  private String directions;
  private String description;
  private Difficulty difficulty;
  private Set<CategoryDTO> categories = new HashSet<>();
  private Set<IngredientDTO> ingredients = new HashSet<>();

  @JsonIgnore
  public Recipe getModel() {
    return Recipe.builder().id(id).url(url).source(source).note(notes == null ? null : notes.getModel()).prepTime(prepTime)
            .cookTime(cookTime).servings(servings).directions(directions).description(description).difficulty(difficulty)
            .categories(categories.stream().map(CategoryDTO::getModel).collect(Collectors.toSet()))
            .ingredients(ingredients.stream().map(IngredientDTO::getModel).collect(Collectors.toSet()))
            .build();
  }
}
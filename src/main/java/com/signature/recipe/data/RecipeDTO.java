package com.signature.recipe.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.signature.recipe.model.Difficulty;
import com.signature.recipe.model.Ingredient;
import com.signature.recipe.model.Recipe;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.Base64;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;
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
  private Byte[] image;
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

  public byte[] getImage() {
    if (Objects.isNull(image)) {
      return new byte[0];
    }
    byte[] bytes = new byte[image.length];
    for (int i = 0; i < image.length; i++) {
      bytes[i] = image[i];
    }
    return bytes;
  }

  public void setImage(byte[] image) {
    if (Objects.nonNull(image)) {
      Byte[] bytes = new Byte[image.length];
      for (int i = 0; i < image.length; i++) {
        bytes[i] = image[i];
      }
      this.image = bytes;
    }
  }

  public void setImage(Byte[] image) {
    this.image = image;
  }

  public String getBase64Image() {
    return "data:image/jpeg;base64,".concat(Base64.getMimeEncoder().encodeToString(getImage()));
  }

  @JsonIgnore
  public Recipe getModel() {
    return Recipe.builder().id(id).url(url).image(image).source(source).note(notes == null ? null : notes.getModel())
            .prepTime(prepTime).cookTime(cookTime).servings(servings).directions(directions).description(description)
            .difficulty(difficulty).categories(categories.stream().map(CategoryDTO::getModel).collect(Collectors.toSet()))
            .ingredients(ingredients.stream().map(IngredientDTO::getModel).sorted(Comparator.comparingLong(Ingredient::getId))
                    .collect(Collectors.toCollection(LinkedHashSet::new))).build();
  }
}
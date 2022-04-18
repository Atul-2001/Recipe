package com.signature.recipe.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.signature.recipe.model.Ingredient;
import com.signature.recipe.model.Recipe;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IngredientDTO {

  @Id
  private Long id;
  private Long recipeId;
  private BigDecimal amount;
  private String description;
  private UnitOfMeasureDTO unitOfMeasure;

  @JsonIgnore
  public Ingredient getModel() {
    return Ingredient.builder().id(id)
            .recipe(Recipe.builder().id(recipeId).build())
            .amount(amount).description(description)
            .unit(unitOfMeasure.getModel()).build();
  }
}
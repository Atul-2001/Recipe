package com.signature.recipe.service;

import com.signature.recipe.data.IngredientDTO;
import com.signature.recipe.model.Ingredient;
import com.signature.recipe.model.Recipe;
import com.signature.recipe.repository.RecipeRepository;
import com.signature.recipe.repository.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class IngredientService {

  private final RecipeRepository recipeRepository;
  private final UnitOfMeasureRepository unitOfMeasureRepository;

  public IngredientService(final RecipeRepository recipeRepository,
                           final UnitOfMeasureRepository unitOfMeasureRepository) {
    this.recipeRepository = recipeRepository;
    this.unitOfMeasureRepository = unitOfMeasureRepository;
  }

  public IngredientDTO saveOrUpdate(final IngredientDTO ingredientDTO) {
    Optional<Recipe> recipeOptional = recipeRepository.findById(ingredientDTO.getRecipeId());
    if (recipeOptional.isEmpty()) {
      log.error("Recipe not found for id: " + ingredientDTO.getRecipeId());
      return new IngredientDTO();
    } else {
      Recipe recipe = recipeOptional.get();

      Optional<Ingredient> ingredientOptional = recipe.getIngredients().stream()
              .filter(ingredient -> ingredient.getId().equals(ingredientDTO.getId()))
              .findFirst();

      if(ingredientOptional.isPresent()){
        Ingredient ingredientFound = ingredientOptional.get();
        ingredientFound.setDescription(ingredientDTO.getDescription());
        ingredientFound.setAmount(ingredientDTO.getAmount());
        ingredientFound.setUnit(unitOfMeasureRepository
                .findById(ingredientDTO.getUnitOfMeasure().getId())
                .orElseThrow(() -> new RuntimeException("UOM NOT FOUND")));
      } else {
        recipe.addIngredients(ingredientDTO.getModel());
      }

      final Recipe savedRecipe = recipeRepository.save(recipe);

      final Ingredient ingredient = savedRecipe.getIngredients().stream()
              .filter(recipeIngredients -> recipeIngredients.getId().equals(ingredientDTO.getId()))
              .findFirst().orElse(null);

      return Objects.isNull(ingredient) ? null : ingredient.getDTO();
    }
  }

  public IngredientDTO getByRecipeAndId(final Long recipeId, final Long ingredientId) {
    final Optional<Recipe> recipe = recipeRepository.findById(recipeId);

    if (recipe.isEmpty()) {
      log.info("Recipe not found for id {}", recipeId);
      return null;
    }

    final Optional<Ingredient> ingredientOptional = recipe.get().getIngredients().stream()
            .filter(ingredient -> Objects.equals(ingredientId, ingredient.getId())).findFirst();

    if (ingredientOptional.isPresent()) {
      return ingredientOptional.get().getDTO();
    } else {
      log.debug("No ingredient found for id {} in recipe {}", ingredientId, recipeId);
      return null;
    }
  }
}
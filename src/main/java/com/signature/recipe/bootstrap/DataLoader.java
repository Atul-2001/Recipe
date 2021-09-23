package com.signature.recipe.bootstrap;

import com.signature.recipe.model.*;
import com.signature.recipe.repository.CategoryRepository;
import com.signature.recipe.repository.RecipeRepository;
import com.signature.recipe.repository.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public DataLoader(RecipeRepository recipeRepository, CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<>();

        Optional<Category> americanDishOptional = categoryRepository.findByDescription("American Dish");
        if (americanDishOptional.isEmpty()) {
            throw new RuntimeException("Category not found!");
        }

        Optional<Category> mexicanDishOptional = categoryRepository.findByDescription("Mexican Dish");
        if (mexicanDishOptional.isEmpty()) {
            throw new RuntimeException("Category not found!");
        }

        Category americanDish = americanDishOptional.get();
        Category mexicanDish = mexicanDishOptional.get();

        Optional<UnitOfMeasure> noneOptional = unitOfMeasureRepository.findByDescription("");
        if (noneOptional.isEmpty()) {
            throw new RuntimeException("Unit of Measure not found!");
        }

        Optional<UnitOfMeasure> smallOptional = unitOfMeasureRepository.findByDescription("Small");
        if (smallOptional.isEmpty()) {
            throw new RuntimeException("Unit of Measure not found!");
        }

        Optional<UnitOfMeasure> mediumOptional = unitOfMeasureRepository.findByDescription("Medium");
        if (mediumOptional.isEmpty()) {
            throw new RuntimeException("Unit of Measure not found!");
        }

        Optional<UnitOfMeasure> largeOptional = unitOfMeasureRepository.findByDescription("Large");
        if (largeOptional.isEmpty()) {
            throw new RuntimeException("Unit of Measure not found!");
        }

        Optional<UnitOfMeasure> ripeOptional = unitOfMeasureRepository.findByDescription("Ripe");
        if (ripeOptional.isEmpty()) {
            throw new RuntimeException("Unit of Measure not found!");
        }

        Optional<UnitOfMeasure> pintOptional = unitOfMeasureRepository.findByDescription("Pint");
        if (pintOptional.isEmpty()) {
            throw new RuntimeException("Unit of Measure not found!");
        }

        Optional<UnitOfMeasure> cupOptional = unitOfMeasureRepository.findByDescription("Cup");
        if (cupOptional.isEmpty()) {
            throw new RuntimeException("Unit of Measure not found!");
        }

        Optional<UnitOfMeasure> cupsOptional = unitOfMeasureRepository.findByDescription("Cups");
        if (cupsOptional.isEmpty()) {
            throw new RuntimeException("Unit of Measure not found!");
        }

        Optional<UnitOfMeasure> cloveOptional = unitOfMeasureRepository.findByDescription("Clove");
        if (cloveOptional.isEmpty()) {
            throw new RuntimeException("Unit of Measure not found!");
        }

        Optional<UnitOfMeasure> teaspoonOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        if (teaspoonOptional.isEmpty()) {
            throw new RuntimeException("Unit of Measure not found!");
        }

        Optional<UnitOfMeasure> tablespoonOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
        if (tablespoonOptional.isEmpty()) {
            throw new RuntimeException("Unit of Measure not found!");
        }

        Optional<UnitOfMeasure> pinchOptional = unitOfMeasureRepository.findByDescription("Pinch");
        if (pinchOptional.isEmpty()) {
            throw new RuntimeException("Unit of Measure not found!");
        }

        Optional<UnitOfMeasure> ounceOptional = unitOfMeasureRepository.findByDescription("Ounce");
        if (ounceOptional.isEmpty()) {
            throw new RuntimeException("Unit of Measure not found!");
        }

        UnitOfMeasure none = noneOptional.get();
        UnitOfMeasure small = smallOptional.get();
        UnitOfMeasure medium = mediumOptional.get();
        UnitOfMeasure large = largeOptional.get();
        UnitOfMeasure ripe = ripeOptional.get();
        UnitOfMeasure pint = pintOptional.get();
        UnitOfMeasure cup = cupOptional.get();
        UnitOfMeasure cups = cupsOptional.get();
        UnitOfMeasure clove = cloveOptional.get();
        UnitOfMeasure teaspoon = teaspoonOptional.get();
        UnitOfMeasure tablespoon = tablespoonOptional.get();
        UnitOfMeasure pinch = pinchOptional.get();
        UnitOfMeasure ounce = ounceOptional.get();

        Recipe recipe = new Recipe();
        recipe.setDescription("Guacamole: A Classic Mexican Dish");
        recipe.setPrepTime(10);
        recipe.setCookTime(0);
        recipe.setServings(4);
        recipe.setSource("Simply Recipes");
        recipe.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        recipe.setRating(4);
        recipe.setDifficulty(Difficulty.MODERATE);
        recipe.getCategories().add(mexicanDish);
        recipe.getCategories().add(americanDish);
        recipe.setDirections("Firstly Cut the avocado and then mash the avocado flesh after that add remaining ingredients to taste. Serve immediately");

        recipe.getIngredients().add(new Ingredient(new BigDecimal(2), ripe, "avocados", recipe));
        recipe.getIngredients().add(new Ingredient(BigDecimal.valueOf(1.0 / 4.0), teaspoon, "salt, plus more to taste", recipe));
        recipe.getIngredients().add(new Ingredient(new BigDecimal(1), tablespoon, "fresh lime or lemon juice", recipe));
        recipe.getIngredients().add(new Ingredient(new BigDecimal(4), tablespoon, "minced red onion or thinly sliced green onion", recipe));
        recipe.getIngredients().add(new Ingredient(new BigDecimal(2), none, "serrano (or jalapeño) chilis, stems and seeds removed, minced", recipe));
        recipe.getIngredients().add(new Ingredient(new BigDecimal(2), tablespoon, "cilantro (leaves and tender stems), finely chopped", recipe));
        recipe.getIngredients().add(new Ingredient(null, none, "Pinch freshly ground black pepper", recipe));
        recipe.getIngredients().add(new Ingredient(BigDecimal.valueOf(1.0/2.0), ripe, "tomato, chopped (optional)", recipe));
        recipe.getIngredients().add(new Ingredient(null, none, "Red radish or jicama slices for garnish (optional)", recipe));
        recipe.getIngredients().add(new Ingredient(null, none, "Tortilla chips, to serve", recipe));

        recipe.setNote(new Note("Be careful handling chilis! If using, it's best to wear food-safe gloves. If no gloves are available, wash your hands thoroughly after handling, and do not touch your eyes or the area near your eyes for several hours afterwards.", recipe));
        recipes.add(recipe);

        recipe = new Recipe();
        recipe.setDescription("Spicy Grilled Chicken Tacos");
        recipe.setPrepTime(20);
        recipe.setCookTime(15);
        recipe.setServings(6);
        recipe.setSource("Simply Recipes");
        recipe.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        recipe.setRating(3);
        recipe.setDifficulty(Difficulty.HARD);
        recipe.getCategories().add(americanDish);
        recipe.setDirections("Prepare a gas or charcoal grill for medium-high, direct heat >>>" +
                " Make the marinade and coat the chicken >>> Grill the chicken >>> Warm the tortillas " +
                ">>> Assemble the tacos");

        recipe.getIngredients().add(new Ingredient(new BigDecimal(2), tablespoon, "ancho chili powder", recipe));
        recipe.getIngredients().add(new Ingredient(new BigDecimal(1), teaspoon, "dried oregano", recipe));
        recipe.getIngredients().add(new Ingredient(new BigDecimal(1), teaspoon, "dried cumin", recipe));
        recipe.getIngredients().add(new Ingredient(new BigDecimal(1), teaspoon, "sugar", recipe));
        recipe.getIngredients().add(new Ingredient(BigDecimal.valueOf(1.0/2.0), teaspoon, "salt", recipe));
        recipe.getIngredients().add(new Ingredient(new BigDecimal(1), clove, "garlic, finely chopped", recipe));
        recipe.getIngredients().add(new Ingredient(new BigDecimal(1), tablespoon, "finely grated orange zest", recipe));
        recipe.getIngredients().add(new Ingredient(new BigDecimal(3), tablespoon, "fresh-squeezed orange juice", recipe));
        recipe.getIngredients().add(new Ingredient(new BigDecimal(2), tablespoon, "olive oil", recipe));
        recipe.getIngredients().add(new Ingredient(new BigDecimal(6), none, "skinless, boneless chicken thighs (1 1/4 pounds)", recipe));
        recipe.getIngredients().add(new Ingredient(new BigDecimal(8), small, "corn tortillas", recipe));
        recipe.getIngredients().add(new Ingredient(new BigDecimal(3), cups, "packed baby arugula (3 ounces)", recipe));
        recipe.getIngredients().add(new Ingredient(new BigDecimal(2), medium, "ripe avocados, sliced", recipe));
        recipe.getIngredients().add(new Ingredient(new BigDecimal(4), none, "radishes, thinly sliced", recipe));
        recipe.getIngredients().add(new Ingredient(BigDecimal.valueOf(1.0/2.0), pint, "cherry tomatoes, halved", recipe));
        recipe.getIngredients().add(new Ingredient(BigDecimal.valueOf(1.0/4.0), none, "red onion, thinly sliced", recipe));
        recipe.getIngredients().add(new Ingredient(null, none, "Roughly chopped cilantro", recipe));
        recipe.getIngredients().add(new Ingredient(BigDecimal.valueOf(1.0/2.0), cup, "sour cream thinned with 1/4 cup milk", recipe));
        recipe.getIngredients().add(new Ingredient(new BigDecimal(1), none, ", cut into wedges", recipe));

        recipe.setNote(new Note("Look for ancho chile powder with the Mexican ingredients at your grocery store, on buy it online. (If you can't find ancho chili powder, you replace the ancho chili, the oregano, and the cumin with 2 1/2 tablespoons regular chili powder, though the flavor won't be quite the same.)", recipe));
        recipes.add(recipe);

        return recipes;
    }
}
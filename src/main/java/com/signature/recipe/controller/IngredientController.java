package com.signature.recipe.controller;

import com.signature.recipe.model.Recipe;
import com.signature.recipe.service.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/recipe/{id}")
public class IngredientController {

  private final RecipeService recipeService;

  public IngredientController(final RecipeService recipeService) {
    this.recipeService = recipeService;
  }

  @GetMapping("/ingredients")
  public String listIngredients(@PathVariable String id, Model model) {
    log.debug("Getting ingredient list for recipe id: " + id);
    final Recipe recipe = recipeService.getById(Long.parseLong(id));
    model.addAttribute("recipe", recipe.getDTO());
    return "/recipe/ingredient/index";
  }
}
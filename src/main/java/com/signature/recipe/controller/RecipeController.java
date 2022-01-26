package com.signature.recipe.controller;

import com.signature.recipe.service.RecipeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Log4j2
@Controller
public class RecipeController {

  private final RecipeService recipeService;

  public RecipeController(RecipeService recipeService) {
    this.recipeService = recipeService;
  }

  @GetMapping("/recipe/show/{id}")
  public String showById(@PathVariable String id, final Model model) {
    log.info("Get recipe for id " + id);
    model.addAttribute("recipe", recipeService.getById(Long.parseLong(id)));
    return "recipe/show";
  }
}
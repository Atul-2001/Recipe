package com.signature.recipe.controller;

import com.signature.recipe.data.RecipeDTO;
import com.signature.recipe.exceptions.NotFoundException;
import com.signature.recipe.model.Recipe;
import com.signature.recipe.service.RecipeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Log4j2
@Controller
@RequestMapping("/recipe")
public class RecipeController {

  private final RecipeService recipeService;

  public RecipeController(RecipeService recipeService) {
    this.recipeService = recipeService;
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(NotFoundException.class)
  public ModelAndView handleNotFound(Exception exception){
    log.error("Handling not found exception");
    log.error("Message : {}", exception.getMessage());
    return new ModelAndView("404")
            .addObject("ex", exception);
  }

  @GetMapping("/new")
  public String createRecipe(final Model model) {
    model.addAttribute("recipe", new RecipeDTO());
    return "recipe/form";
  }

  @GetMapping("/{id}/show")
  public String showById(@PathVariable String id, final Model model) {
    log.info("Get recipe for id " + id);
    model.addAttribute("recipe", recipeService.getById(Long.parseLong(id)));
    return "recipe/show";
  }

  @GetMapping("/{id}/update")
  public String updateRecipe(@PathVariable String id, final Model model) {
    model.addAttribute("recipe", recipeService.getById(Long.parseLong(id)).getDTO());
    return "recipe/form";
  }

  @PostMapping
  public String addOrUpdate(@ModelAttribute final RecipeDTO recipeDTO) {
    final Recipe recipe = recipeService.save(recipeDTO);
    return "redirect:/recipe/".concat(String.valueOf(recipe.getId())).concat("/show");
  }

  @GetMapping("/{id}/delete")
  public String deleteRecipe(@PathVariable String id) {
    recipeService.deleteById(Long.parseLong(id));
    return "redirect:/";
  }
}
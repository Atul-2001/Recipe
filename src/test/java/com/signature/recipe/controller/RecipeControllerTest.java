package com.signature.recipe.controller;

import com.signature.recipe.model.Recipe;
import com.signature.recipe.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class RecipeControllerTest {

  @Mock
  public RecipeService recipeService;

  public RecipeController recipeController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    recipeController = new RecipeController(recipeService);
  }

  @Test
  void showById() throws Exception {
    Recipe recipe = Recipe.builder().id(1L).build();

    when(recipeService.getById(anyLong())).thenReturn(recipe);

    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();

    mockMvc.perform(get("/recipe/show/1"))
            .andExpect(status().isOk())
            .andExpect(view().name("recipe/show"))
            .andExpect(model().attribute("recipe", instanceOf(Recipe.class)));
  }
}
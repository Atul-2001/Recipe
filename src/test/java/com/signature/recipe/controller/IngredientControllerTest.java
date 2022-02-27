package com.signature.recipe.controller;

import com.signature.recipe.data.RecipeDTO;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class IngredientControllerTest {

  private MockMvc mockMvc;

  @Mock
  public RecipeService recipeService;

  public IngredientController ingredientController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    this.ingredientController = new IngredientController(recipeService);

    this.mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
  }

  @Test
  void listIngredients() throws Exception {
    //given
    Recipe recipe = Recipe.builder().id(1L).build();

    when(recipeService.getById(anyLong())).thenReturn(recipe);

    //when
    mockMvc.perform(get("/recipe/1/ingredients"))
            .andExpect(status().isOk())
            .andExpect(view().name("/recipe/ingredient/index"))
            .andExpect(model().attribute("recipe", instanceOf(RecipeDTO.class)));

    //then
    verify(recipeService, times(1)).getById(anyLong());
  }
}
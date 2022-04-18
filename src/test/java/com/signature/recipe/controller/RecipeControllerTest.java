package com.signature.recipe.controller;

import com.signature.recipe.data.RecipeDTO;
import com.signature.recipe.model.Recipe;
import com.signature.recipe.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    mockMvc.perform(get("/recipe/1/show"))
            .andExpect(status().isOk())
            .andExpect(view().name("recipe/show"))
            .andExpect(model().attribute("recipe", instanceOf(Recipe.class)));
  }

  @Test
  public void createRecipe() throws Exception {
    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();

    mockMvc.perform(get("/recipe/new"))
            .andExpect(status().isOk())
            .andExpect(view().name("recipe/form"))
            .andExpect(model().attribute("recipe", instanceOf(RecipeDTO.class)));
  }

  @Test
  public void updateRecipe() throws Exception {
    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();

    when(recipeService.getById(anyLong())).thenReturn(Recipe.builder().id(1L).build());

    mockMvc.perform(get("/recipe/1/update"))
            .andExpect(status().isOk())
            .andExpect(view().name("recipe/form"))
            .andExpect(model().attribute("recipe", instanceOf(RecipeDTO.class)));
  }

  @Test
  public void addOrUpdate() throws Exception {
    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();

    when(recipeService.save(any())).thenReturn(Recipe.builder().id(2L).build());
    when(recipeService.getById(anyLong())).thenReturn(Recipe.builder().id(2L).build());

    mockMvc.perform(post("/recipe").contentType(MediaType.APPLICATION_FORM_URLENCODED))
            .andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/recipe/2/show"));
  }

  @Test
  void deleteRecipe() throws Exception {
    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();

    mockMvc.perform(get("/recipe/1/delete"))
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/"));

    verify(recipeService, times(1)).deleteById(anyLong());
  }
}
package guru.springframework.controllers;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IndexControllerTest {

  IndexController indexController;

  @Mock
  RecipeService recipeService;

  @Mock
  Model model;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);

    indexController = new IndexController(recipeService);
  }

  @Test
  public void testMockMvc() throws Exception {
    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();

    mockMvc.perform(get("/"))
        .andExpect(status().isOk())
        .andExpect(view().name("index"));
  }

  @Test
  public void showIndex() throws Exception {

    // given
    Set<Recipe> recipes = new HashSet<>();
    recipes.add(new Recipe(1L));
    recipes.add(new Recipe(2L));

    when(recipeService.getAllRecipes()).thenReturn(recipes);

    ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

    // when
    String viewName = indexController.showIndex(model);

    // then
    assertTrue("View is index", viewName.matches("index"));
    verify(recipeService, times(1)).getAllRecipes();
    verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
    Set<Recipe> setInController = argumentCaptor.getValue();
    assertEquals(2, setInController.size());

  }

}
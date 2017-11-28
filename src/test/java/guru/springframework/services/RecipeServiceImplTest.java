package guru.springframework.services;

import guru.springframework.converters.RecipeCommandToRecipe;
import guru.springframework.converters.RecipeToRecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

  RecipeServiceImpl recipeService;

  @Mock
  RecipeCommandToRecipe recipeCommandToRecipe;

  @Mock
  RecipeToRecipeCommand recipeToRecipeCommand;

  @Mock
  RecipeRepository recipeRepository;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);

    recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
  }

  @Test
  public void getRecipeByIdTest() throws Exception {
    Recipe recipe = new Recipe();
    recipe.setId(1L);
    Optional<Recipe> recipeOptional = Optional.of(recipe);

    when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

    Recipe recipeReturned = recipeService.findById(1L);

    assertNotNull("Null recipe returned", recipeReturned);
    verify(recipeRepository, times(1)).findById(anyLong());
    verify(recipeRepository, never()).findAll();

  }

  @Test
  public void getAllRecipes() throws Exception {

    Recipe recipe = new Recipe();
    List<Recipe> recipesData = new ArrayList<>();
    recipesData.add(recipe);

    when(recipeRepository.findAll()).thenReturn(recipesData);

    Set<Recipe> recipes = recipeService.getAllRecipes();
     
    assertEquals(1, recipes.size());
    verify(recipeRepository, times(1)).findAll();
  }

}
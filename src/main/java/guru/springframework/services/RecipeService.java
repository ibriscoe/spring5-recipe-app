package guru.springframework.services;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;

import java.util.Set;

public interface RecipeService {

  public Recipe findById(Long id);
  public Set<Recipe> getAllRecipes();
  public RecipeCommand saveRecipeCommand(RecipeCommand command);
}

package com.tmploeg.recipes;

import com.tmploeg.recipes.models.Ingredient;
import com.tmploeg.recipes.models.Recipe;
import com.tmploeg.recipes.models.RecipeIngredient;
import com.tmploeg.recipes.repositories.IngredientRepository;
import com.tmploeg.recipes.repositories.RecipeIngredientRepository;
import com.tmploeg.recipes.repositories.RecipeRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Seeder implements CommandLineRunner {
  private final RecipeRepository recipeRepository;
  private final IngredientRepository ingredientRepository;
  private final RecipeIngredientRepository recipeIngredientRepository;

  @Override
  public void run(String... args) throws Exception {
    if (recipeRepository.findAll().isEmpty()) {
      seedRecipes();
    }

    if (ingredientRepository.findAll().isEmpty()) {
      seedIngredients();
    }

    if (recipeIngredientRepository.findAll().isEmpty()) {
      seedRecipeIngredients();
    }
  }

  private void seedRecipes() {
    List<Recipe> recipes = new ArrayList<>(5);

    recipes.add(new Recipe("Pepperoni pizza"));
    recipes.add(new Recipe("Spaghetti"));
    recipes.add(new Recipe("Irish coffee"));
    recipes.add(new Recipe("Apple pie"));
    recipes.add(new Recipe("Ice cream"));

    recipeRepository.saveAll(recipes);
  }

  private void seedIngredients() {
    List<Ingredient> ingredients = new ArrayList<>(5);

    ingredients.add(new Ingredient("Wheat"));
    ingredients.add(new Ingredient("Milk"));
    ingredients.add(new Ingredient("Eggs"));
    ingredients.add(new Ingredient("Flour"));
    ingredients.add(new Ingredient("Salt"));

    ingredientRepository.saveAll(ingredients);
  }

  private void seedRecipeIngredients() {
    Map<Long, Long[]> recipeIngredientList =
        Map.of(
            1L,
            new Long[] {1L, 2L},
            2L,
            new Long[] {3L, 4L},
            3L,
            new Long[] {2L, 5L},
            4L,
            new Long[] {1L, 5L},
            5L,
            new Long[] {1L, 3L});

    for (Long recipeId : recipeIngredientList.keySet()) {
      Optional<Recipe> maybeRecipe = recipeRepository.findById(recipeId);
      if (maybeRecipe.isEmpty()) {
        continue;
      }

      for (Long ingredientId : recipeIngredientList.get(recipeId)) {
        Optional<Ingredient> maybeIngredient = ingredientRepository.findById(ingredientId);
        if (maybeIngredient.isEmpty()) {
          continue;
        }

        recipeIngredientRepository.save(
            new RecipeIngredient(maybeRecipe.get(), maybeIngredient.get(), 1, "L"));
      }
    }
  }
}

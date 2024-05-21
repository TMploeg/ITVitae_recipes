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
    Map<Integer, int[]> recipeIngredientList =
        Map.of(
            0,
            new int[] {0, 1},
            1,
            new int[] {2, 3},
            2,
            new int[] {1, 4},
            3,
            new int[] {0, 4},
            4,
            new int[] {0, 2});

    for (int recipeIndex : recipeIngredientList.keySet()) {
      Recipe recipe = recipeRepository.findAll().get(recipeIndex);

      for (int ingredientIndex : recipeIngredientList.get(recipeIndex)) {
        Ingredient ingredient = ingredientRepository.findAll().get(ingredientIndex);

        recipeIngredientRepository.save(new RecipeIngredient(recipe, ingredient, 1, "L"));
      }
    }
  }
}

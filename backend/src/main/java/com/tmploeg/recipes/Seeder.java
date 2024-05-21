package com.tmploeg.recipes;

import com.tmploeg.recipes.models.Ingredient;
import com.tmploeg.recipes.models.Recipe;
import com.tmploeg.recipes.repositories.IngredientRepository;
import com.tmploeg.recipes.repositories.RecipeRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Seeder implements CommandLineRunner {
  private final RecipeRepository recipeRepository;
  private final IngredientRepository ingredientRepository;

  @Override
  public void run(String... args) throws Exception {
    if (recipeRepository.findAll().isEmpty()) {
      seedRecipes();
    }

    if (ingredientRepository.findAll().isEmpty()) {
      seedIngredients();
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
}

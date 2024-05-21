package com.tmploeg.recipes.dtos;

import com.tmploeg.recipes.models.Recipe;

public record RecipeDTO(String title) {
  public static RecipeDTO from(Recipe recipe) {
    if (recipe == null) {
      throw new IllegalArgumentException("recipe must not be null");
    }

    return new RecipeDTO(recipe.getTitle());
  }
}

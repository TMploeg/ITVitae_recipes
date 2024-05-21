package com.tmploeg.recipes.dtos;

import com.tmploeg.recipes.models.Ingredient;

public record IngredientDTO(String name) {
  public static IngredientDTO from(Ingredient ingredient) {
    if (ingredient == null) {
      throw new IllegalArgumentException("ingredient must not be null");
    }

    return new IngredientDTO(ingredient.getName());
  }
}

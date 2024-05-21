package com.tmploeg.recipes.dtos;

import com.tmploeg.recipes.models.Ingredient;

public record IngredientDTO(Long id, String name) {
  public static IngredientDTO from(Ingredient ingredient) {
    if (ingredient == null) {
      throw new IllegalArgumentException("ingredient must not be null");
    }

    return new IngredientDTO(ingredient.getId(), ingredient.getName());
  }
}

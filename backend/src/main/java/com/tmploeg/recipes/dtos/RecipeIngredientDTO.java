package com.tmploeg.recipes.dtos;

import com.tmploeg.recipes.models.RecipeIngredient;

public record RecipeIngredientDTO(Long ingredientId, String name, double quantity, String unit) {
  public static RecipeIngredientDTO from(RecipeIngredient recipeIngredient) {
    if (recipeIngredient == null) {
      throw new IllegalArgumentException("recipeIngredient is null");
    }

    return new RecipeIngredientDTO(
        recipeIngredient.getIngredient().getId(),
        recipeIngredient.getIngredient().getName(),
        recipeIngredient.getQuantity(),
        recipeIngredient.getUnit());
  }
}

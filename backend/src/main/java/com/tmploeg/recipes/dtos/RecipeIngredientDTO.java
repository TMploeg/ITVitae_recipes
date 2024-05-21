package com.tmploeg.recipes.dtos;

import com.tmploeg.recipes.models.RecipeIngredient;

public record RecipeIngredientDTO(Long recipeId, Long ingredientId) {
  public static RecipeIngredientDTO from(RecipeIngredient recipeIngredient) {
    if (recipeIngredient == null) {
      throw new IllegalArgumentException("recipeIngredient is null");
    }

    return new RecipeIngredientDTO(
        recipeIngredient.getRecipe().getId(), recipeIngredient.getIngredient().getId());
  }
}

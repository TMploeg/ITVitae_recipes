package com.tmploeg.recipes.dtos;

import com.tmploeg.recipes.models.Recipe;
import com.tmploeg.recipes.models.RecipeIngredient;
import java.util.Collection;
import java.util.List;

public record RecipeInfoDTO(Long id, String title, List<RecipeIngredientDTO> ingredients) {
  public static RecipeInfoDTO from(Recipe recipe, Collection<RecipeIngredient> ingredients) {
    return new RecipeInfoDTO(
        recipe.getId(),
        recipe.getTitle(),
        ingredients.stream().map(RecipeIngredientDTO::from).toList());
  }
}

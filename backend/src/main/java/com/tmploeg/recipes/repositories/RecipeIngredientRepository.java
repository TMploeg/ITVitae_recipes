package com.tmploeg.recipes.repositories;

import com.tmploeg.recipes.models.RecipeIngredient;
import com.tmploeg.recipes.models.embeddedkeys.RecipeIngredientKey;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeIngredientRepository
    extends JpaRepository<RecipeIngredient, RecipeIngredientKey> {
  Set<RecipeIngredient> findByRecipe_id(Long recipeId);
}

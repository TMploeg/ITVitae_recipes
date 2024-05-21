package com.tmploeg.recipes.models.embeddedkeys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeIngredientKey implements Serializable {
  @Column(name = "recipe_id")
  private Long recipeId;

  @Column(name = "ingredient_id")
  private Long ingredientId;

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof RecipeIngredientKey key)) {
      return false;
    }

    return this.recipeId.equals(key.recipeId) && this.ingredientId.equals(key.recipeId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(recipeId, ingredientId);
  }
}

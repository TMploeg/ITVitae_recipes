package com.tmploeg.recipes.models;

import com.tmploeg.recipes.models.embeddedkeys.RecipeIngredientKey;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class RecipeIngredient {
  @EmbeddedId private RecipeIngredientKey key = new RecipeIngredientKey();

  @ManyToOne
  @MapsId("recipeId")
  @JoinColumn(name = "recipe_id")
  private Recipe recipe;

  @ManyToOne
  @MapsId("ingredientId")
  @JoinColumn(name = "ingredient_id")
  private Ingredient ingredient;

  private double quantity;

  private String unit;

  public RecipeIngredient(Recipe recipe, Ingredient ingredient, double quantity, String unit) {
    this.recipe = recipe;
    this.ingredient = ingredient;
    this.quantity = quantity;
    this.unit = unit;
  }
}

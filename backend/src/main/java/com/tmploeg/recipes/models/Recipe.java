package com.tmploeg.recipes.models;

import jakarta.persistence.*;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Recipe {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  @OneToMany(mappedBy = "recipe")
  private Set<RecipeIngredient> recipeIngredients;

  public Recipe(String title) {
    this.title = title;
  }
}

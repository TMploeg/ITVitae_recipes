package com.tmploeg.recipes.models;

import jakarta.persistence.*;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Ingredient {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @OneToMany(mappedBy = "ingredient")
  private Set<RecipeIngredient> ingredientRecipes;

  public Ingredient(String name) {
    this.name = name;
  }
}

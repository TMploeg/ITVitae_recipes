package com.tmploeg.recipes.repositories;

import com.tmploeg.recipes.models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {}

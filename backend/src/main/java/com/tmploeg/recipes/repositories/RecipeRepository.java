package com.tmploeg.recipes.repositories;

import com.tmploeg.recipes.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    
}

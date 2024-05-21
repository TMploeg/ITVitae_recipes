package com.tmploeg.recipes.controllers;

import com.tmploeg.recipes.models.Recipe;
import com.tmploeg.recipes.repositories.RecipeRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/recipes")
public class RecipeController {
  private final RecipeRepository recipeRepository;

  @GetMapping
  public List<Recipe> getAll() {
    return recipeRepository.findAll();
  }

  @GetMapping("{id}")
  public ResponseEntity<Recipe> getById(@PathVariable Long id) {
    return recipeRepository
        .findById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }
}

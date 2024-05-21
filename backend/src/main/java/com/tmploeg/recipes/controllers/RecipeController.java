package com.tmploeg.recipes.controllers;

import com.tmploeg.recipes.dtos.RecipeDTO;
import com.tmploeg.recipes.models.Recipe;
import com.tmploeg.recipes.repositories.RecipeRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

  @PostMapping
  public ResponseEntity<?> addRecipe(@RequestBody RecipeDTO recipeDTO) {
    if (recipeDTO.title() == null) {
      return ResponseEntity.badRequest().body("title is required");
    }

    if (recipeDTO.title().isBlank()) {
      return ResponseEntity.badRequest().body("title must have at least one character");
    }

    return ResponseEntity.ok(recipeRepository.save(new Recipe(recipeDTO.title())));
  }
}

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
@CrossOrigin("${app.cors}")
public class RecipeController {
  private final RecipeRepository recipeRepository;

  @GetMapping
  public List<RecipeDTO> getAll() {
    return recipeRepository.findAll().stream().map(RecipeDTO::from).toList();
  }

  @GetMapping("{id}")
  public ResponseEntity<RecipeDTO> getById(@PathVariable Long id) {
    return recipeRepository
        .findById(id)
        .map(r -> ResponseEntity.ok(RecipeDTO.from(r)))
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

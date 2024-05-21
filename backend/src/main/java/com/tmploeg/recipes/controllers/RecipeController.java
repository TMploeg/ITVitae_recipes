package com.tmploeg.recipes.controllers;

import com.tmploeg.recipes.dtos.RecipeDTO;
import com.tmploeg.recipes.dtos.RecipeInfoDTO;
import com.tmploeg.recipes.models.Recipe;
import com.tmploeg.recipes.repositories.RecipeIngredientRepository;
import com.tmploeg.recipes.repositories.RecipeRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ControllerRoutes.RECIPE_ROUTE)
@CrossOrigin("${app.cors}")
public class RecipeController {
  private final RecipeRepository recipeRepository;
  private final RecipeIngredientRepository recipeIngredientRepository;

  @GetMapping
  public List<RecipeDTO> getAll() {
    return recipeRepository.findAll().stream().map(RecipeDTO::from).toList();
  }

  @GetMapping("{id}")
  public ResponseEntity<RecipeInfoDTO> getById(@PathVariable Long id) {
    Optional<Recipe> maybeRecipe = recipeRepository.findById(id);
    return maybeRecipe
        .map(
            recipe ->
                ResponseEntity.ok(
                    RecipeInfoDTO.from(
                        recipe, recipeIngredientRepository.findByRecipe_id(recipe.getId()))))
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

package com.tmploeg.recipes.controllers;

import com.tmploeg.recipes.dtos.AddIngredientDTO;
import com.tmploeg.recipes.dtos.IngredientDTO;
import com.tmploeg.recipes.dtos.RecipeIngredientDTO;
import com.tmploeg.recipes.models.Ingredient;
import com.tmploeg.recipes.models.Recipe;
import com.tmploeg.recipes.models.RecipeIngredient;
import com.tmploeg.recipes.models.embeddedkeys.RecipeIngredientKey;
import com.tmploeg.recipes.repositories.IngredientRepository;
import com.tmploeg.recipes.repositories.RecipeIngredientRepository;
import com.tmploeg.recipes.repositories.RecipeRepository;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping(ControllerRoutes.RECIPE_INGREDIENT_ROUTE)
@CrossOrigin("${app.cors}")
public class RecipeIngredientController {
  private final RecipeIngredientRepository recipeIngredientRepository;
  private final IngredientRepository ingredientRepository;
  private final RecipeRepository recipeRepository;

  @GetMapping
  public List<IngredientDTO> getAll(@PathVariable Long recipeId) {
    return recipeIngredientRepository.findByRecipe_id(recipeId).stream()
        .map(rI -> IngredientDTO.from(rI.getIngredient()))
        .toList();
  }

  @GetMapping("{ingredientId}")
  public ResponseEntity<RecipeIngredientDTO> getById(
      @PathVariable Long recipeId, @PathVariable Long ingredientId) {
    return recipeIngredientRepository
        .findById(new RecipeIngredientKey(recipeId, ingredientId))
        .map(rI -> ResponseEntity.ok(RecipeIngredientDTO.from(rI)))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<?> addIngredientToRecipe(
      @PathVariable Long recipeId,
      @RequestBody AddIngredientDTO addIngredientDTO,
      UriComponentsBuilder ucb) {
    List<String> missingFields = new LinkedList<>();
    if (addIngredientDTO.ingredientId() == null) {
      missingFields.add("ingredientId");
    }
    if (addIngredientDTO.quantity() == null) {
      missingFields.add("quantity");
    }
    if (addIngredientDTO.unit() == null) {
      missingFields.add("unit");
    }

    if (!missingFields.isEmpty()) {
      return ResponseEntity.badRequest()
          .body("fields (" + String.join(", ", missingFields) + ") are required");
    }

    Optional<Recipe> maybeRecipe = recipeRepository.findById(recipeId);
    if (maybeRecipe.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    Optional<Ingredient> maybeIngredient =
        ingredientRepository.findById(addIngredientDTO.ingredientId());
    if (maybeIngredient.isEmpty()) {
      return ResponseEntity.badRequest()
          .body("no ingredient with id '" + addIngredientDTO.ingredientId() + "' exists");
    }

    RecipeIngredient recipeIngredient =
        recipeIngredientRepository.save(
            new RecipeIngredient(
                maybeRecipe.get(),
                maybeIngredient.get(),
                addIngredientDTO.quantity(),
                addIngredientDTO.unit()));

    URI uri =
        ucb.path(ControllerRoutes.RECIPE_INGREDIENT_ROUTE + "/{ingredientId}")
            .build(maybeRecipe.get().getId(), maybeIngredient.get().getId());

    return ResponseEntity.created(uri).body(recipeIngredient);
  }
}

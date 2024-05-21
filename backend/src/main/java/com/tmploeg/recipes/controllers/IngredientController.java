package com.tmploeg.recipes.controllers;

import com.tmploeg.recipes.dtos.IngredientDTO;
import com.tmploeg.recipes.models.Ingredient;
import com.tmploeg.recipes.repositories.IngredientRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(ControllerRoutes.INGREDIENT_ROUTE)
@CrossOrigin("${app.cors}")
public class IngredientController {
  private final IngredientRepository ingredientRepository;

  @GetMapping
  public List<IngredientDTO> getAll() {
    return ingredientRepository.findAll().stream().map(IngredientDTO::from).toList();
  }

  @GetMapping("{id}")
  public ResponseEntity<IngredientDTO> getById(@PathVariable Long id) {
    return ingredientRepository
        .findById(id)
        .map(i -> ResponseEntity.ok(IngredientDTO.from(i)))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<?> addIngredient(@RequestBody IngredientDTO ingredientDTO) {
    if (ingredientDTO.name() == null) {
      return ResponseEntity.badRequest().body("name is required");
    }
    if (ingredientDTO.name().isBlank()) {
      return ResponseEntity.badRequest().body("name must have at least one character");
    }

    return ResponseEntity.ok(ingredientRepository.save(new Ingredient(ingredientDTO.name())));
  }
}

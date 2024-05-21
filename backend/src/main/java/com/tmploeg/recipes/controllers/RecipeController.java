package com.tmploeg.recipes.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/recipes")
public class RecipeController {
    @GetMapping
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("hello recipes!");
    }
}


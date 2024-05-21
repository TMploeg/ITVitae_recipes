package com.tmploeg.recipes;

import com.tmploeg.recipes.models.Recipe;
import com.tmploeg.recipes.repositories.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Seeder implements CommandLineRunner {
    private final RecipeRepository recipeRepository;

    @Override
    public void run(String... args) throws Exception {
        if(!recipeRepository.findAll().isEmpty()){
            return;
        }

        List<Recipe> recipes = new ArrayList<>(5);

        recipes.add(new Recipe("Pepperoni pizza"));
        recipes.add(new Recipe("Spaghetti"));
        recipes.add(new Recipe("Irish coffee"));
        recipes.add(new Recipe("Apple pie"));
        recipes.add(new Recipe("Ice cream"));

        recipeRepository.saveAll(recipes);
    }
}

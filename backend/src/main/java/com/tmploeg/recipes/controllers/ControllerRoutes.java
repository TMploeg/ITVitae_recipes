package com.tmploeg.recipes.controllers;

public abstract class ControllerRoutes {
  private static final String DEFAULT_ROUTE = "api/v1";

  public static final String RECIPE_ROUTE = DEFAULT_ROUTE + "/recipes";
  public static final String INGREDIENT_ROUTE = DEFAULT_ROUTE + "/ingredients";
  public static final String RECIPE_INGREDIENT_ROUTE =
      DEFAULT_ROUTE + "/recipes/{recipeId}/ingredients";
}

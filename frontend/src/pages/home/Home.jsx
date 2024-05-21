import axios from "axios";
import { useEffect, useState } from "react";
import { API_URL } from "../../App";
import NewRecipeInput from "../../components/new-recipe-input/NewRecipeInput";
import RecipeList from "../../components/recipe-list";
import "./Home.css";

export default function Home() {
    const [recipes, setRecipes] = useState(null);

    const API_RECIPES_URL = API_URL + 'recipes';

    useEffect(loadRecipes, []);

    return <div className="homepage-container">
        <h1 className="homepage-title">Recipes</h1>
        <NewRecipeInput onSubmit={submitNewRecipe} />
        {
            recipes === null ? 'loading...' : <RecipeList recipes={recipes} />
        }
    </div>

    function submitNewRecipe(newRecipe) {
        axios.post(API_RECIPES_URL, { title: newRecipe })
            .then(_ => loadRecipes())
            .catch(console.error);
    }

    function loadRecipes() {
        axios.get(API_RECIPES_URL)
            .then(response => setRecipes(response.data))
            .catch(error => {
                console.error(error);
                setRecipes([]);
            });
    }
}

import axios from "axios";
import { useEffect, useState } from "react";
import { API_URL } from "../../App";
import RecipeList from "../../components/recipe-list";
import "./Home.css";

export default function Home() {
    const [recipes, setRecipes] = useState(null);
    const [newRecipeName, setNewRecipeName] = useState('');

    const API_RECIPES_URL = API_URL + 'recipes';

    useEffect(loadRecipes, []);

    return <div className="homepage-container">
        <h1 className="homepage-title">Recipes</h1>
        <div className="homepage-menu">
            <input value={newRecipeName} onChange={event => setNewRecipeName(event.target.value)} />
            <button disabled={newRecipeName.length === 0} onClick={submitNewRecipe}>Add Recipe</button>
        </div>
        {
            recipes === null ? 'loading...' : <RecipeList recipes={recipes} />
        }
    </div>

    function submitNewRecipe() {
        axios.post(API_RECIPES_URL, { title: newRecipeName })
            .then(_ => loadRecipes())
            .catch(console.error)
            .finally(() => setNewRecipeName(''));
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

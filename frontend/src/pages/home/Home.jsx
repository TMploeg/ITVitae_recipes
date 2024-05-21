import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { API_URL } from "../../App";
import ItemList from "../../components/item-list";
import "./Home.css";

export default function Home() {
    const [recipes, setRecipes] = useState(null);
    const navigate = useNavigate();

    const API_RECIPES_URL = API_URL + 'recipes';

    useEffect(loadRecipes, []);

    return <div className="page-container">
        <h1 className="page-title">Recipes</h1>
        {
            recipes === null ? 'loading...' : <ItemList items={recipes} displayItemFn={item => item.title} onItemClicked={item => navigate('/recipe/' + item.id)} />
        }
        <button className="add-recipe-button" onClick={() => navigate('/add-recipe')}>Add Recipe</button>
    </div>

    function submitNewRecipe(newRecipe) {
        axios.post(API_RECIPES_URL, { title: newRecipe })
            .then(_ => loadRecipes())
            .catch(error => console.error(`ERROR: ${error.response.data}`));
    }

    function loadRecipes() {
        axios.get(API_RECIPES_URL)
            .then(response => setRecipes(response.data))
            .catch(error => {
                console.error(`ERROR: ${error.response.data}`);
                setRecipes([]);
            });
    }
}

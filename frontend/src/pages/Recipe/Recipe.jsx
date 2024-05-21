import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { API_URL } from "../../App";
import ItemList from "../../components/item-list";

export default function Recipe() {
    const { id } = useParams();
    const navigate = useNavigate();
    const [recipe, setRecipe] = useState(null);
    const [recipeIngredients, setRecipeIngredients] = useState(null);

    useEffect(() => loadRecipeIngredients(), []);

    const API_RECIPE_URL = API_URL + `recipes/${id}`;
    const API_RECIPE_INGREDIENTS_URL = API_URL + `recipes/${id}/ingredients`;

    if (recipe === null) {
        return <div>loading...</div>
    }

    return <div className="page-container">
        <h1 className="page-title">{recipe.title}</h1>
        <h2 className="page-subtitle">Ingredients</h2>
        {
            recipeIngredients == null
                ? 'loading...'
                : <ItemList items={recipeIngredients} displayItemFn={item => item.name} />
        }
    </div>

    function loadRecipeIngredients() {
        axios.get(API_RECIPE_URL)
            .then(response => {
                setRecipe(response.data);
                axios.get(API_RECIPE_INGREDIENTS_URL)
                    .then(response => setRecipeIngredients(response.data))
                    .catch(error => console.error(error.response.data));
            })
            .catch(_ => navigate('/'));
    }
}
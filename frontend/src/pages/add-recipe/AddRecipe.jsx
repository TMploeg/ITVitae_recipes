import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { API_URL } from "../../App";
import "./AddRecipe.css";

export default function AddRecipe() {
    const [newRecipeName, setNewRecipeName] = useState('');

    const [newIngredient, setNewIngredient] = useState('');
    const [newIngredientQuantity, setNewIngredientQuantity] = useState(0);
    const [newIngredientUnit, setNewIngredientUnit] = useState('');

    const [recipeIngredients, setRecipeIngredients] = useState([]);
    const [ingredients, setIngredients] = useState(null);

    const navigate = useNavigate();

    useEffect(loadIngredients, []);

    const API_RECIPES_URL = API_URL + 'recipes';
    const API_INGREDIENTS_URL = API_URL + 'ingredients';

    return <div className="page-container add-recipe-page">
        <h1 className="page-title">New Recipe</h1>
        <label>
            recipe name <input value={newRecipeName} onChange={event => setNewRecipeName(event.target.value)} />
        </label>
        <div className="add-recipe-ingredient-form">
            <h2 className="page-subtitle">Add Ingredient</h2>
            <label>
                name <select onChange={event => setNewIngredient(ingredients[event.target.selectedIndex])}>
                    {(ingredients ?? []).map(i => <option key={i.id}>{i.name}</option>)}
                </select>
            </label>
            <label>
                quantity <input value={newIngredientQuantity} onChange={event => setNewIngredientQuantity(event.target.value)} />
            </label>
            <label>
                unit <input value={newIngredientUnit} onChange={event => setNewIngredientUnit(event.target.value)} />
            </label>
            <button onClick={addIngredient}>Add Ingredient</button>
        </div>
        <div>
            <h2 className="list-subtitle">Ingredients</h2>
            <div>
                {recipeIngredients.map((ingredient, index) => <div key={index}>
                    {ingredient.quantity}{ingredient.unit} {ingredient.value.name}
                </div>)}
            </div>
        </div>
        <button disabled={newRecipeName.length === 0} onClick={submit}>Add Recipe</button>
    </div>

    function loadIngredients() {
        axios.get(API_INGREDIENTS_URL)
            .then(getIngredientsResponse => {
                setIngredients(getIngredientsResponse.data);
                if (getIngredientsResponse.data.length > 0) {
                    setNewIngredient(getIngredientsResponse.data[0])
                }
            })
            .catch(_ => navigate('/'));
    }

    function addIngredient() {
        setRecipeIngredients(ingredients => [
            ...ingredients,
            {
                value: newIngredient,
                quantity: newIngredientQuantity,
                unit: newIngredientUnit
            }
        ]);

        setNewIngredient('');
        setNewIngredientQuantity(0);
        setNewIngredientUnit('');
    }

    function submit() {
        axios.post(API_RECIPES_URL, { title: newRecipeName })
            .then(addRecipeResponse => {
                const newRecipe = addRecipeResponse.data;
                const recipeIngredientsURL = API_RECIPES_URL + `/${newRecipe.id}/ingredients`;
                const recipeIngredientPromises = [];

                for (let ingredient of recipeIngredients) {

                    console.log(ingredient);
                    recipeIngredientPromises.push(axios.post(
                        recipeIngredientsURL,
                        {
                            ingredientId: ingredient.value.id,
                            quantity: ingredient.quantity,
                            unit: ingredient.unit
                        })
                        .catch(error => {
                            console.error(error.response.data);
                            alert('unknown error occurred');
                        }));
                }

                Promise.all(recipeIngredientPromises).then(_ => navigate('/recipe/' + newRecipe.id));
            })
            .catch(error => alert(error.response.data));
    }
}
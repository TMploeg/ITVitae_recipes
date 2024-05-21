import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { API_URL } from "../../App";
import "./AddRecipe.css";

export default function AddRecipe() {
    const [newRecipeName, setNewRecipeName] = useState('');

    const [newIngredientName, setNewIngredientName] = useState('');
    const [newIngredientQuantity, setNewIngredientQuantity] = useState(0);
    const [newIngredientUnit, setNewIngredientUnit] = useState('');

    const [ingredients, setIngredients] = useState([]);

    const navigate = useNavigate();

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
                name <input value={newIngredientName} onChange={event => setNewIngredientName(event.target.value)} />
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
                {ingredients.map(i => <div>{i.quantity}{i.unit} {i.name}</div>)}
            </div>
        </div>
        <button disabled={newRecipeName.length === 0} onClick={submit}>Add Recipe</button>
    </div>

    function addIngredient() {
        setIngredients(ingredients => [
            ...ingredients,
            {
                name: newIngredientName,
                quantity: newIngredientQuantity,
                unit: newIngredientUnit
            }
        ]);

        setNewIngredientName('');
        setNewIngredientQuantity(0);
        setNewIngredientUnit('');
    }

    function submit() {
        axios.post(API_RECIPES_URL, { title: newRecipeName })
            .then(addRecipeResponse => {
                const newRecipe = addRecipeResponse.data;
                const recipeIngredientsURL = API_RECIPES_URL + `/${newRecipe.id}/ingredients`;

                axios.get(API_INGREDIENTS_URL)
                    .then(getIngredientsResponse => {
                        const recipeIngredientPromises = [];

                        for (let ingredient of ingredients) {
                            const foundIngredient = getIngredientsResponse.data.find(item => item.name === ingredient.name);
                            if (foundIngredient === undefined) {
                                return Promise.reject({
                                    response: {
                                        data: "ingredient does not exist"
                                    }
                                });
                            }

                            recipeIngredientPromises.push(axios.post(
                                recipeIngredientsURL,
                                {
                                    ingredientId: foundIngredient.id,
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
                    .catch(error => {
                        console.log(error);
                        alert(error.response.data)
                    });
            })
            .catch(error => alert(error.response.data));
    }
}
import axios from "axios";
import { useEffect, useState } from "react";
import { API_URL } from "../../App";
import ItemList from "../../components/item-list";
import TextInput from "../../components/text-input";

export default function Ingredients() {
    const [ingredients, setIngredients] = useState(null);

    const API_INGREDIENTS_URL = API_URL + 'ingredients';

    useEffect(loadIngredients, []);

    return <div className="page-container">
        <h1 className="page-title">Ingredients</h1>
        <TextInput onSubmit={submitNewIngredient} />
        {
            ingredients === null ? 'loading...' : <ItemList items={ingredients} displayItemFn={item => item.name} />
        }
    </div>

    function submitNewIngredient(newIngredient) {
        axios.post(API_INGREDIENTS_URL, { name: newIngredient })
            .then(_ => loadIngredients())
            .catch(error => console.error(`ERROR: ${error.response.data}`));
    }

    function loadIngredients() {
        axios.get(API_INGREDIENTS_URL)
            .then(response => setIngredients(response.data))
            .catch(error => {
                console.error(error);
                setIngredients([]);
            });
    }
}
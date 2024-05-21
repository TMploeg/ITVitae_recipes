import axios from "axios";
import { useEffect, useState } from "react";
import { API_URL } from "../../App";

export default function Home() {
    const [recipes, setRecipes] = useState(null);

    useEffect(() => {
        axios.get(API_URL + 'recipes')
            .then(response => setRecipes(response.data))
            .catch(error => {
                console.error(error);
                setRecipes([]);
            });
    }, []);

    return <div>
        <h1>Recipes</h1>
        <div>
            {
                recipes === null
                    ? 'loading...'
                    : recipes.length === 0
                        ? 'no recipes found'
                        : recipes.map((recipe, index) => <div key={index}>{recipe.title}</div>)
            }
        </div>
    </div>
}

import axios from "axios";
import { useEffect, useState } from "react";
import { API_URL } from "../../App";
import "./Home.css";

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

    return <div className="homepage-container">
        <h1 className="homepage-title">Recipes</h1>
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

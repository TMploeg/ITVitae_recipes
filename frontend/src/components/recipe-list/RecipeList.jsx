export default function RecipeList({ recipes }) {
    if (recipes === undefined || recipes === null) {
        return null;
    }

    return <div>
        {
            recipes.length === 0
                ? 'no recipes found'
                : recipes.map((recipe, index) => <div key={index}>{recipe.title}</div>)
        }
    </div>

}
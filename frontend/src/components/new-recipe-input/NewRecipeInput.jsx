import { useState } from "react";

export default function NewRecipeInput({ onSubmit }) {
    const [newRecipeName, setNewRecipeName] = useState('');

    return <div>
        <input value={newRecipeName} onChange={event => setNewRecipeName(event.target.value)} />
        <button disabled={newRecipeName.length === 0} onClick={onSubmitClicked}>Add Recipe</button>
    </div>

    function onSubmitClicked() {
        setNewRecipeName('');
        onSubmit(newRecipeName);
    }
}
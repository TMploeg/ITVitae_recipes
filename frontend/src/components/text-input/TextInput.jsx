import { useState } from "react";

export default function TextInput({ onSubmit }) {
    const [text, setText] = useState('');

    return <div>
        <input value={text} onChange={event => setText(event.target.value)} />
        <button disabled={text.length === 0} onClick={onSubmitClicked}>Add</button>
    </div>

    function onSubmitClicked() {
        setText('');
        onSubmit(text);
    }
}
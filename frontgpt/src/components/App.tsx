import { useState } from "react";
import { sendMessage } from "../services/api";

const App = () => {
    const [message, setMessage] = useState("");
    const [response, setResponse] = useState("");

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        try {
            const data = await sendMessage(message);
            setResponse(data);
        } catch {
            setResponse("Error al comunicarse con el backend.");
        }

    };


    return (
        <div>
            <h1>¡Bienvenido a FrontGPT!</h1>
            <form onSubmit={handleSubmit}>
                <input
                    type="text"
                    value={message}
                    onChange={(e) => setMessage(e.target.value)}
                    placeholder="Escribe un mensaje"
                />
                <button type="submit">Enviar</button>
            </form>
            {response && <p>Respuesta del backend: {response}</p>}
        </div>
    );
};

export default App;

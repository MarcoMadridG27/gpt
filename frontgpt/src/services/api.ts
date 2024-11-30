import axios from "axios";

const api = axios.create({
    baseURL: import.meta.env.VITE_BACKEND_URL,
});

export const sendMessage = async (content: string): Promise<string> => {
    try {
        const response = await api.post("/messages", { content });
        return response.data;
    } catch (error) {
        console.error("Error al enviar el mensaje:", error);
        throw error;
    }
};

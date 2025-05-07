package com.dbp.gpt.dto;

public class IAResponseDTO {
    private String prompt;
    private String response;
    private String modelo;
    private int tokensConsumidos;
    private String timestamp;

    public IAResponseDTO(String content, String response, String s, int i, String string) {
    }

    public IAResponseDTO() {

    }

    public IAResponseDTO(String content, String respuesta, String modelo, int tokensConsumidos) {
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getTokensConsumidos() {
        return tokensConsumidos;
    }

    public void setTokensConsumidos(int tokensConsumidos) {
        this.tokensConsumidos = tokensConsumidos;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}

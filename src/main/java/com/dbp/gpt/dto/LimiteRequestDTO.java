// dto/LimiteRequestDTO.java
package com.dbp.gpt.dto;

public class LimiteRequestDTO {
    private String email;
    private String modelo;
    private int solicitudes;
    private int tokens;

    // Getters y setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public int getSolicitudes() { return solicitudes; }
    public void setSolicitudes(int solicitudes) { this.solicitudes = solicitudes; }

    public int getTokens() { return tokens; }
    public void setTokens(int tokens) { this.tokens = tokens; }
}

package com.dbp.gpt.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class LimiteUsuario {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private User usuario;

    private String modelo;
    private int solicitudesRestantes;
    private int tokensRestantes;
    private LocalDateTime fechaUltimoReseteo;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getFechaUltimoReseteo() {
        return fechaUltimoReseteo;
    }

    public void setFechaUltimoReseteo(LocalDateTime fechaUltimoReseteo) {
        this.fechaUltimoReseteo = fechaUltimoReseteo;
    }

    public int getTokensRestantes() {
        return tokensRestantes;
    }

    public void setTokensRestantes(int tokensRestantes) {
        this.tokensRestantes = tokensRestantes;
    }

    public int getSolicitudesRestantes() {
        return solicitudesRestantes;
    }

    public void setSolicitudesRestantes(int solicitudesRestantes) {
        this.solicitudesRestantes = solicitudesRestantes;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }
// Getters y setters
}
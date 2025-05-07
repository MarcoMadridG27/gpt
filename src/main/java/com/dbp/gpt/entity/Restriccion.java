package com.dbp.gpt.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class Restriccion {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private Empresa empresa;

    private String modelo;
    private int maxTokens;
    private int maxRequests;
    private String ventanaTiempo; // por ejemplo: "1h", "24h"

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getVentanaTiempo() {
        return ventanaTiempo;
    }

    public void setVentanaTiempo(String ventanaTiempo) {
        this.ventanaTiempo = ventanaTiempo;
    }

    public int getMaxTokens() {
        return maxTokens;
    }

    public void setMaxTokens(int maxTokens) {
        this.maxTokens = maxTokens;
    }

    public int getMaxRequests() {
        return maxRequests;
    }

    public void setMaxRequests(int maxRequests) {
        this.maxRequests = maxRequests;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}
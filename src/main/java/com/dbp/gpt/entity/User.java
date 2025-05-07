package com.dbp.gpt.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String rol; // ROLE_SPARKY_ADMIN, etc.

    @ManyToOne
    private Empresa empresa;

    // 🔧 Constructor vacío requerido por JPA
    public User() {}

    // 🔧 Constructor opcional útil
    public User(UUID id, Empresa empresa, String rol, String password, String email, String nombre) {
        this.id = id;
        this.empresa = empresa;
        this.rol = rol;
        this.password = password;
        this.email = email;
        this.nombre = nombre;
    }

    // ✅ Getters y setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

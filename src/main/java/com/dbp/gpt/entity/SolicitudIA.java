package com.dbp.gpt.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
public class SolicitudIA {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private User usuario;

    @ManyToOne
    private Empresa empresa;

    private String modelo;
    private String consulta;
    private String respuesta;
    private int tokensConsumidos;
    private LocalDateTime fecha;

    // 👇 Nuevo campo para guardar solo nombres de archivo
    @ElementCollection
    @CollectionTable(name = "solicitud_archivos", joinColumns = @JoinColumn(name = "solicitud_id"))
    @Column(name = "archivo")
    private List<String> archivosAdjuntos;

    // Constructor vacío
    public SolicitudIA() {}

    // Constructor completo (opcional)
    public SolicitudIA(UUID id, LocalDateTime fecha, int tokensConsumidos, String respuesta,
                       String consulta, String modelo, Empresa empresa, User usuario,
                       List<String> archivosAdjuntos) {
        this.id = id;
        this.fecha = fecha;
        this.tokensConsumidos = tokensConsumidos;
        this.respuesta = respuesta;
        this.consulta = consulta;
        this.modelo = modelo;
        this.empresa = empresa;
        this.usuario = usuario;
        this.archivosAdjuntos = archivosAdjuntos;
    }

    // Getters y setters

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public User getUsuario() { return usuario; }
    public void setUsuario(User usuario) { this.usuario = usuario; }

    public Empresa getEmpresa() { return empresa; }
    public void setEmpresa(Empresa empresa) { this.empresa = empresa; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getConsulta() { return consulta; }
    public void setConsulta(String consulta) { this.consulta = consulta; }

    public String getRespuesta() { return respuesta; }
    public void setRespuesta(String respuesta) { this.respuesta = respuesta; }

    public int getTokensConsumidos() { return tokensConsumidos; }
    public void setTokensConsumidos(int tokensConsumidos) { this.tokensConsumidos = tokensConsumidos; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public List<String> getArchivosAdjuntos() { return archivosAdjuntos; }
    public void setArchivosAdjuntos(List<String> archivosAdjuntos) { this.archivosAdjuntos = archivosAdjuntos; }
}

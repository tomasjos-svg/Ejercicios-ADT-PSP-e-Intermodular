package com.practica_5.gestion_biblioteca.model;

import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "prestamos")
public class Prestamo {

    @Id
    private String id;

    // Referencia al libro prestado (almacenamos el ID del libro)
    private String libroId;

    // Referencia al usuario que realiza el préstamo
    private String usuarioId;

    private Date fechaPrestamo;

    public Prestamo(String id, String libroId, String usuarioId, Date fechaPrestamo) {
        super();
        this.id = id;
        this.libroId = libroId;
        this.usuarioId = usuarioId;
        this.fechaPrestamo = fechaPrestamo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLibroId() {
        return libroId;
    }

    public void setLibroId(String libroId) {
        this.libroId = libroId;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }
}
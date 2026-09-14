package com.ejemplo.centro.modelo;

import java.io.Serializable;
import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "EXPEDIENTE")
public class Expediente implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "DNI_ALUMNO", nullable = false, unique = true)
    private Alumno alumno;

    @Column(name = "FECHA_APERTURA", nullable = false)
    private LocalDate fechaApertura;

    @Column(name = "OBSERVACIONES", length = 255)
    private String observaciones;

    public Expediente() {}
    public Expediente(Alumno alumno, LocalDate fechaApertura, String observaciones) {
        this.alumno = alumno; this.fechaApertura = fechaApertura; this.observaciones = observaciones;
    }
    public Integer getId() { return id; }
    public Alumno getAlumno() { return alumno; }
    public void setAlumno(Alumno alumno) { this.alumno = alumno; }
    public LocalDate getFechaApertura() { return fechaApertura; }
    public void setFechaApertura(LocalDate fechaApertura) { this.fechaApertura = fechaApertura; }
    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
    @Override
    public String toString() { return "Expediente{id=" + id + ", fechaApertura=" + fechaApertura + ", observaciones='" + observaciones + "'}"; }
}

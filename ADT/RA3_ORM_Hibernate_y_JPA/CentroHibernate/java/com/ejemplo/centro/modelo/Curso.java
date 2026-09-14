package com.ejemplo.centro.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "CURSO")
public class Curso implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CODIGO")
    private Integer codigo;

    @Column(name = "NOMBRE", nullable = false, length = 80)
    private String nombre;

    @Column(name = "DESCRIPCION", length = 255)
    private String descripcion;

    @OneToMany(mappedBy = "curso")
    private List<Alumno> alumnos = new ArrayList<>();

    public Curso() {}
    public Curso(Integer codigo, String nombre, String descripcion) {
        this.codigo = codigo; this.nombre = nombre; this.descripcion = descripcion;
    }
    public Integer getCodigo() { return codigo; }
    public void setCodigo(Integer codigo) { this.codigo = codigo; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public List<Alumno> getAlumnos() { return alumnos; }
    public void setAlumnos(List<Alumno> alumnos) { this.alumnos = alumnos; }

    @Override
    public String toString() { return "Curso{codigo=" + codigo + ", nombre='" + nombre + "'}"; }
}

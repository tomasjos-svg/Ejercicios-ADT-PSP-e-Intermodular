package com.ejemplo.centro.modelo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ASIGNATURA")
public class Asignatura implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CODIGO")
    private Integer codigo;

    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "DESCRIPCION", length = 255)
    private String descripcion;

    @ManyToMany(mappedBy = "asignaturas")
    private Set<Profesor> profesores = new HashSet<>();

    public Asignatura() {}
    public Asignatura(Integer codigo, String nombre, String descripcion) {
        this.codigo = codigo; this.nombre = nombre; this.descripcion = descripcion;
    }
    public Integer getCodigo() { return codigo; }
    public void setCodigo(Integer codigo) { this.codigo = codigo; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public Set<Profesor> getProfesores() { return profesores; }
    public void setProfesores(Set<Profesor> profesores) { this.profesores = profesores; }
    @Override
    public String toString() { return "Asignatura{codigo=" + codigo + ", nombre='" + nombre + "'}"; }
}

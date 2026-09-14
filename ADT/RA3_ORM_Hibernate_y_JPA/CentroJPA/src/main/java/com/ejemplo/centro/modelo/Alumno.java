package com.ejemplo.centro.modelo;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "ALUMNO")
@PrimaryKeyJoinColumn(name = "DNI")
public class Alumno extends Persona implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "COD_ALUMNO", nullable = false, unique = true, length = 20)
    private String codAlumno;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "COD_CURSO", nullable = false)
    private Curso curso;

    @OneToOne(mappedBy = "alumno", fetch = FetchType.LAZY)
    private Expediente expediente;

    public Alumno() {}
    public Alumno(String dni, String nombre, String apellidos, String codAlumno, Curso curso) {
        super(dni, nombre, apellidos);
        this.codAlumno = codAlumno;
        this.curso = curso;
    }
    public String getCodAlumno() { return codAlumno; }
    public void setCodAlumno(String codAlumno) { this.codAlumno = codAlumno; }
    public Curso getCurso() { return curso; }
    public void setCurso(Curso curso) { this.curso = curso; }
    public Expediente getExpediente() { return expediente; }
    public void setExpediente(Expediente expediente) { this.expediente = expediente; }
    @Override
    public String toString() {
        return "Alumno{dni='" + getDni() + "', nombre='" + getNombre() + "', apellidos='" + getApellidos() + "', codAlumno='" + codAlumno + "'}";
    }
}

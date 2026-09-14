package com.ejemplo.centro.modelo;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
@Entity
@Table(name = "PROFESOR")
@PrimaryKeyJoinColumn(name = "DNI")
public class Profesor extends Persona implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "NRP", nullable = false, unique = true, length = 20)
    private String nrp;

    @Column(name = "ESPECIALIDAD", nullable = false, length = 100)
    private String especialidad;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_DEPARTAMENTO", nullable = false)
    private Departamento departamento;

    @ManyToMany
    @JoinTable(name = "PROFESOR_ASIGNATURA",
        joinColumns = @JoinColumn(name = "DNI_PROFESOR"),
        inverseJoinColumns = @JoinColumn(name = "COD_ASIGNATURA"))
    private Set<Asignatura> asignaturas = new HashSet<>();

    public Profesor() {}
    public Profesor(String dni, String nombre, String apellidos, String nrp,
                    String especialidad, Departamento departamento) {
        super(dni, nombre, apellidos);
        this.nrp = nrp; this.especialidad = especialidad; this.departamento = departamento;
    }
    public String getNrp() { return nrp; }
    public void setNrp(String nrp) { this.nrp = nrp; }
    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
    public Departamento getDepartamento() { return departamento; }
    public void setDepartamento(Departamento departamento) { this.departamento = departamento; }
    public Set<Asignatura> getAsignaturas() { return asignaturas; }
    public void setAsignaturas(Set<Asignatura> asignaturas) { this.asignaturas = asignaturas; }

    public void addAsignatura(Asignatura asignatura) {
        asignaturas.add(asignatura);
        asignatura.getProfesores().add(this);
    }

    public void removeAsignatura(Asignatura asignatura) {
        asignaturas.remove(asignatura);
        asignatura.getProfesores().remove(this);
    }

    @Override
    public String toString() {
        return "Profesor{dni='" + getDni() + "', nombre='" + getNombre() + "', nrp='" + nrp + "', especialidad='" + especialidad + "'}";
    }
}

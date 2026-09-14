package com.practica.gestionempresa.model;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
@Entity
@Table(name = "empleados")
@PrimaryKeyJoinColumn(name = "persona_id")
public class Empleado extends Persona {
    @NotBlank(message = "El puesto es obligatorio")
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String puesto;
    @NotNull(message = "El salario es obligatorio")
    @DecimalMin(value = "0.01", message = "El salario debe ser mayor que cero")
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal salario;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departamento_id", nullable = false)
    private Departamento departamento;
    @JsonIgnore
    @OneToMany(mappedBy = "empleado")
    private List<Hijo> hijos = new ArrayList<>();
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "empleado_habilidad",
        joinColumns = @JoinColumn(name = "empleado_id"),
        inverseJoinColumns = @JoinColumn(name = "habilidad_id")
    )
    private Set<Habilidad> habilidades = new HashSet<>();
    @JsonIgnore
    @OneToMany(mappedBy = "director")
    private List<CentroTrabajo> centrosDirigidos = new ArrayList<>();
    public Empleado() {
    }
    public Empleado(String nombre, String apellidos, String documento,
            String puesto, BigDecimal salario) {
        super(nombre, apellidos, documento);
        this.puesto = puesto;
        this.salario = salario;
    }
    public String getPuesto() {
        return puesto;
    }
    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }
    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public List<Hijo> getHijos() {
        return hijos;
    }

    public void setHijos(List<Hijo> hijos) {
        this.hijos = hijos;
    }

    public Set<Habilidad> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(Set<Habilidad> habilidades) {
        this.habilidades = habilidades;
    }

    public List<CentroTrabajo> getCentrosDirigidos() {
        return centrosDirigidos;
    }

    public void setCentrosDirigidos(List<CentroTrabajo> centrosDirigidos) {
        this.centrosDirigidos = centrosDirigidos;
    }
}
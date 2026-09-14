package com.practica.gestionempresa.model;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
@Entity
@Table(name = "departamentos")
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "El nombre del departamento es obligatorio")
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String nombre;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "centro_id", nullable = false)
    private CentroTrabajo centro;
    @JsonIgnore
    @OneToMany(mappedBy = "departamento")
    private List<Empleado> empleados = new ArrayList<>();
    public Departamento() {    }
    public Departamento(String nombre) {
        this.nombre = nombre;
    }
    public Long getId() {  return id;  }
    public void setId(Long id) { this.id = id;  }
    public String getNombre() {  return nombre;  }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public CentroTrabajo getCentro() {   return centro;    }
    public void setCentro(CentroTrabajo centro) {    this.centro = centro;   }
    public List<Empleado> getEmpleados() {    return empleados;    }

    public void setEmpleados(List<Empleado> empleados) {  this.empleados = empleados;  }
}

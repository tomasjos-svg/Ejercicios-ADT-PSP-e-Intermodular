package com.practica.gestionempresa.model;
import java.util.HashSet;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
@Entity
@Table(name = "habilidades")
public class Habilidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "El nombre de la habilidad es obligatorio")
    @Size(max = 100)
    @Column(nullable = false, unique = true, length = 100)
    private String nombre;
    @Size(max = 300)
    @Column(length = 300)
    private String descripcion;
    @JsonIgnore
    @ManyToMany(mappedBy = "habilidades")
    private Set<Empleado> empleados = new HashSet<>();
    public Habilidad() {    }
    public Habilidad(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    public Long getId() {   return id;   }
    public void setId(Long id) {  this.id = id;  }
    public String getNombre() { return nombre;  }
    public void setNombre(String nombre) {   this.nombre = nombre;    }
    public String getDescripcion() {  return descripcion;  }
    public void setDescripcion(String descripcion) {   this.descripcion = descripcion;   }
    public Set<Empleado> getEmpleados() {   return empleados;   }
    public void setEmpleados(Set<Empleado> empleados) {
        this.empleados = empleados;
    }
}

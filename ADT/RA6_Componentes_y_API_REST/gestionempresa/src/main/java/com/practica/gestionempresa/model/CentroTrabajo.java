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
@Table(name = "centros_trabajo")
public class CentroTrabajo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "El nombre del centro es obligatorio")
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String nombre;
    @NotBlank(message = "La direccion es obligatoria")
    @Size(max = 200)
    @Column(nullable = false, length = 200)
    private String direccion;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "director_id")
    private Empleado director;
    @JsonIgnore
    @OneToMany(mappedBy = "centro")
    private List<Departamento> departamentos = new ArrayList<>();
    public CentroTrabajo() {   }
    public CentroTrabajo(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
    }
    public Long getId() {   return id;  }
    public void setId(Long id) {   this.id = id;  }
    public String getNombre() {   return nombre;  }
    public void setNombre(String nombre) {    this.nombre = nombre;   }
    public String getDireccion() {   return direccion;  }
    public void setDireccion(String direccion) {   this.direccion = direccion;   }
    public Empleado getDirector() {    return director; }
    public void setDirector(Empleado director) {   this.director = director;  }
    public List<Departamento> getDepartamentos() {  return departamentos;  }
    public void setDepartamentos(List<Departamento> departamentos) {
    	this.departamentos = departamentos;
    }
}
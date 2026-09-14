package com.practica.gestionempresa.model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
@Entity
@Table(name = "personas")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nombre;
    @NotBlank(message = "Los apellidos son obligatorios")
    @Size(max = 150, message = "Los apellidos no pueden superar los 150 caracteres")
    @Column(nullable = false, length = 150)
    private String apellidos;
    @NotBlank(message = "El documento es obligatorio")
    @Size(max = 30, message = "El documento no puede superar los 30 caracteres")
    @Column(nullable = false, unique = true, length = 30)
    private String documento;
    public Persona() {
    }
    public Persona(String nombre, String apellidos, String documento) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.documento = documento;
    }
    public Long getId() { return id; }

    public void setId(Long id) {    this.id = id;    }

    public String getNombre() {     return nombre;    }

    public void setNombre(String nombre) {   this.nombre = nombre;    }

    public String getApellidos() {   return apellidos;  }

    public void setApellidos(String apellidos) {  this.apellidos = apellidos;   }

    public String getDocumento() { return documento; }

    public void setDocumento(String documento) {  this.documento = documento; }
}
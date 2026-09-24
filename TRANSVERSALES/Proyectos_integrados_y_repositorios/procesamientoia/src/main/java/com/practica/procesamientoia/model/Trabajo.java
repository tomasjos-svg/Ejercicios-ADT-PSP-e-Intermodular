package com.practica.procesamientoia.model;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
@Entity
@Table (name = "trabajo")
public class Trabajo {
  public Trabajo(@NotBlank(message = "El nombre es obligatorio") @Size(max = 100) String nombre,
			LocalDateTime fechaCreacion, EstadoTrabajo estado) {
		super();
		this.nombre = nombre;
		this.fechaCreacion = fechaCreacion;
		this.estado = estado;
	}
  public Trabajo() {}
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotBlank(message = "El nombre es obligatorio")
  @Size(max = 100)
  @Column(nullable = false, length = 100)
  private String nombre;
  @Column(name="FECHS_CREACION", nullable=false)
  private LocalDateTime fechaCreacion;
  @Enumerated(EnumType.STRING)
  @Column(name = "ESTADO", nullable = false)
  private EstadoTrabajo estado;
  public Long getId() {
	return id;
  }
  public void setId(long id) {
	this.id = id;
  }
  public String getNombre() {
	return nombre;
  }
  public void setNombre(String nombre) {
	this.nombre = nombre;
  }
  public LocalDateTime getFechaCreacion() {
	return fechaCreacion;
  }
  public void setFechaCreacion(LocalDateTime fechaCreacion) {
	this.fechaCreacion = fechaCreacion;
  }
  public EstadoTrabajo getEstado() {
	return estado;
  }
  public void setEstado(EstadoTrabajo estado) {
	this.estado = estado;
  }
  
}

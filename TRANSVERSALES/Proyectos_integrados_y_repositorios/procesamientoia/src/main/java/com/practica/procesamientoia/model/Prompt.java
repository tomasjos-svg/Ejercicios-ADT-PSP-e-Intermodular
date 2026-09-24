package com.practica.procesamientoia.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
@Entity
@Table(name="Prompt")
public class Prompt {
	public Prompt(@NotBlank(message = "El mensaje es obligatorio") @Size(max = 2000) String texto,
			EstadoPrompt estado, Trabajo trabajo, String mensajeError) {
		super();
		
		this.texto = texto;
		this.estado = estado;
		this.trabajo = trabajo;
		this.mensajeError = mensajeError;
	}
	public Prompt() {
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "El mensaje es obligatorio")
	@Size(max = 2000)
	@Column(nullable = false, length = 2000)
	private String texto;
	@Enumerated(EnumType.STRING)
	@Column(name = "ESTADO", nullable = false)
	private EstadoPrompt estado;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "TRABAJO_ID", nullable = false)
	@JsonIgnore
	private Trabajo trabajo;
	@Column(name="MENSAJE_ERROR", length = 1000, nullable=true)
	private String mensajeError;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public EstadoPrompt getEstado() {
		return estado;
	}
	public void setEstado(EstadoPrompt estado) {
		this.estado = estado;
	}
	public Trabajo getTrabajo() {
		return trabajo; 
	}
	public void setTrabajo(Trabajo trabajo) {
		this.trabajo = trabajo;
	}
	public String getMensajeError() {
		return mensajeError;
	}
	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}
}

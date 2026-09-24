package com.practica.procesamientoia.model;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
@Entity
@Table(name="Respuesta")
public class Respuesta {
	public Respuesta( @NotBlank(message = "El mensaje es obligatorio") @Size(max = 2000) String texto,
			LocalDateTime fechaCreacion, Prompt prompt) {
		super();	
		
		this.texto = texto;
		this.fechaCreacion = fechaCreacion;
		this.prompt = prompt;
	}
	public Respuesta() {}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "El mensaje es obligatorio")
	@Lob
	@Column(nullable = false, length = 2000)
	private String texto;
	@Column(name="FECHA_CREACION", nullable=false)
	  private LocalDateTime fechaCreacion;
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "PROMPT_ID", nullable = false, unique = true)
	@JsonIgnore
	private Prompt prompt;
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
	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Prompt getPrompt() {
		return prompt;
	}
	public void setPrompt(Prompt prompt) {
		this.prompt = prompt;
	}
	@JsonProperty("promptId")
	public Long getPromptId() {
	    return prompt != null ? prompt.getId() : null;
	}
}

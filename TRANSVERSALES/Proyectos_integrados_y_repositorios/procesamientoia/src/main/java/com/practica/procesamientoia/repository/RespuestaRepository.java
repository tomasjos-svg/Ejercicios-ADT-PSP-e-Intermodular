package com.practica.procesamientoia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practica.procesamientoia.model.Prompt;
import com.practica.procesamientoia.model.Respuesta;
import com.practica.procesamientoia.model.Trabajo;

public interface RespuestaRepository extends JpaRepository<Respuesta, Long>{
	List<Respuesta> findByPromptTrabajoId(Long trabajoId);
}

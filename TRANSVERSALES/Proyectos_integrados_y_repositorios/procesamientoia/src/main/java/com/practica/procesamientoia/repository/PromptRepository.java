package com.practica.procesamientoia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practica.procesamientoia.model.EstadoPrompt;
import com.practica.procesamientoia.model.Prompt;
import com.practica.procesamientoia.model.Trabajo;

public interface PromptRepository extends JpaRepository<Prompt, Long>{
	List<Prompt> findByTrabajoIdOrderByIdAsc(Long trabajoId);
	<Optionl>Prompt findFirstByTrabajoIdAndEstadoOrderByIdAsc(Long trabajoId, EstadoPrompt estado);
	long countByTrabajoIdAndEstado(Long trabajoId, EstadoPrompt estado);
}

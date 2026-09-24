package com.practica.procesamientoia.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.practica.procesamientoia.model.Prompt;
import com.practica.procesamientoia.model.Respuesta;
import com.practica.procesamientoia.model.Trabajo;
import com.practica.procesamientoia.service.TrabajoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/trabajos")
public class TrabajoController {
	private final TrabajoService service;

	public TrabajoController(TrabajoService service) {
		super();
		this.service = service;
	}
	@PostMapping("/importar")
	public ResponseEntity<Trabajo> importarTrabajo(@Valid @RequestParam("nombre") String nombre,
			@Valid @RequestParam ("fichero") MultipartFile fichero) {
		
		 return ResponseEntity
	                .status(HttpStatus.CREATED)
	                .body(service.crearTrabajoDesdeCsv(nombre,fichero));
			
	}
	@GetMapping()
    public List<Trabajo> listarTodos() {
        return service.listarTrabajos();
    }
	@GetMapping("/{id}")
	public Trabajo obtenerTrabajo(@PathVariable Long id) {
        return service.buscarPorId(id);
	}
	@GetMapping("/{id}/prompts")
	    public List<Prompt> listarPrompts(
	            @PathVariable Long id) {

	        return service.listarPrompts(id);
	}
	@GetMapping("/{id}/respuestas")
    public List<Respuesta> listarRespuestas(
            @PathVariable Long id) {

        return service.listarRespuestas(id);
    }
	@GetMapping("/{id}/progreso")
	public Map<String,Long> obtenerProgreso(@PathVariable Long id){
		
		return service.obtenerProgreso(id);
		
	}
}

package com.practica.procesamientoia.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.practica.procesamientoia.exception.RecursoNoEncontradoException;

import com.practica.procesamientoia.exception.CsvInvalidoException;
import com.practica.procesamientoia.model.EstadoPrompt;
import com.practica.procesamientoia.model.EstadoTrabajo;
import com.practica.procesamientoia.model.Prompt;
import com.practica.procesamientoia.model.Respuesta;
import com.practica.procesamientoia.model.Trabajo;
import com.practica.procesamientoia.repository.PromptRepository;
import com.practica.procesamientoia.repository.RespuestaRepository;
import com.practica.procesamientoia.repository.TrabajoRepository;
@Service
@Transactional
public class TrabajoService {
	public TrabajoService(TrabajoRepository trabajoRepository, PromptRepository promptRepository,
			RespuestaRepository respuestaRepository) {
		super();
		this.trabajoRepository = trabajoRepository;
		this.promptRepository = promptRepository;
		this.respuestaRepository = respuestaRepository;
	}
	private final TrabajoRepository trabajoRepository;
	private final PromptRepository promptRepository;
	private final RespuestaRepository respuestaRepository;
	public Trabajo crearTrabajoDesdeCsv(String nombre, MultipartFile fichero) {
		Trabajo t=null;
		
			if (nombre == null || nombre.isBlank()) {
		        throw new CsvInvalidoException("El nombre del trabajo es obligatorio");
		    }

		    if (fichero == null || fichero.isEmpty()) {
		        throw new CsvInvalidoException("El fichero CSV es obligatorio y no puede estar vacío");
		    }
		    t=new Trabajo(nombre,LocalDateTime.now(),EstadoTrabajo.PENDIENTE);
		    
		    Trabajo t_guardado= trabajoRepository.save(t);
		    try {
		    BufferedReader lector=new BufferedReader(new InputStreamReader(fichero.getInputStream()));
		    String cadena=lector.readLine();
		 
	        if (cadena == null || !cadena.trim().equalsIgnoreCase("pregunta")) {
	            throw new CsvInvalidoException(
	                    "El CSV debe comenzar con la cabecera pregunta");
	        }
		    if ((!cadena.isEmpty())&&(cadena.equalsIgnoreCase("pregunta"))) {
		    	cadena=lector.readLine();
		    	int numeroPrompts=0;
		    	while(cadena!= null) {
		    		if (!cadena.isBlank()) {
		    			Prompt p=new Prompt(cadena,EstadoPrompt.PENDIENTE,t_guardado,"");
		    			promptRepository.save(p);
		    			numeroPrompts++;
		    		}
		    		cadena=lector.readLine();
		    	}
		    	if (numeroPrompts==0) {
		    		throw new CsvInvalidoException("El documento CSV está vacio");
			    }		    	
		    }
		    }
		    catch(IOException e){
		    	  throw new CsvInvalidoException(
		                    "El documento CSV no se ha podido abrir");
		        }	
		return t_guardado;
	}
	@Transactional(readOnly = true)
	public List<Trabajo> listarTrabajos() {
	     return trabajoRepository.findAll();
	 }
	@Transactional(readOnly = true)
	public Trabajo buscarPorId(Long id) {
	     return trabajoRepository.findById(id)
	             .orElseThrow(() ->
	                 new RecursoNoEncontradoException(
	                     "No existe el trabjo con id " + id));
	}
	@Transactional(readOnly = true)
	public List<Prompt> listarPrompts(Long trabajoId) {
	     Trabajo trabajo = buscarPorId(trabajoId);
	     return promptRepository.findByTrabajoIdOrderByIdAsc(trabajoId);
    }
	public List<Respuesta> listarRespuestas(Long trabajoId){
    	Trabajo trabajo = buscarPorId(trabajoId);
    	return respuestaRepository.findByPromptTrabajoId(trabajoId);    	
    }
    public Map<String, Long> obtenerProgreso(Long trabajoId) {
    	Trabajo trabajo = buscarPorId(trabajoId);
    	long total = promptRepository
                .findByTrabajoIdOrderByIdAsc(trabajoId)
                .size();
        long pendientes = promptRepository
                .countByTrabajoIdAndEstado(
                        trabajoId,
                        EstadoPrompt.PENDIENTE);
        long procesando = promptRepository
                .countByTrabajoIdAndEstado(
                        trabajoId,
                        EstadoPrompt.PROCESANDO);
        long completados = promptRepository
                .countByTrabajoIdAndEstado(
                        trabajoId,
                        EstadoPrompt.COMPLETADO);
        long errores = promptRepository
                .countByTrabajoIdAndEstado(
                        trabajoId,
                        EstadoPrompt.ERROR);
        Map<String, Long> resumen = new LinkedHashMap<>();
        resumen.put("total", total);
        resumen.put("pendientes", pendientes);
        resumen.put("procesando", procesando);
        resumen.put("completados", completados);
        resumen.put("errores", errores);
        return resumen;   	
    	
    }
    public Trabajo guardar(Trabajo trabajo) {
    	return trabajoRepository.save(trabajo);
    }
}


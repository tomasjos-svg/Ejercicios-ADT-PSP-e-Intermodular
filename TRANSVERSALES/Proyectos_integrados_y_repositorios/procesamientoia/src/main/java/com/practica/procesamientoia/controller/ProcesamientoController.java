package com.practica.procesamientoia.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.practica.procesamientoia.service.ProcesamientoService;

@RestController
@RequestMapping("/trabajos")
public class ProcesamientoController {

    private final ProcesamientoService procesamientoService;

    public ProcesamientoController(
            ProcesamientoService procesamientoService) {

        this.procesamientoService = procesamientoService;
    }

    @PostMapping("/{id}/procesar")
    public ResponseEntity<String> procesarTrabajo(
            @PathVariable Long id) {

        procesamientoService.iniciarProcesamiento(id);

        return ResponseEntity.ok(
                "Procesamiento del trabajo " + id + " iniciado");
    }
}

/*
import com.practica.procesamientoia.service.OpenAIService;
import org.springframework.web.bind.annotation.RequestParam;
@RestController
@RequestMapping("/procesamiento")
public class ProcesamientoController {

    private final OpenAIService openAIService;

    public ProcesamientoController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }

    @PostMapping("/prueba-openai")
    public String probarOpenAI(@RequestParam String pregunta) {
        return openAIService.enviarPrompt(pregunta);
    }
    
}

*/
package com.practica.gestionempresa.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practica.gestionempresa.model.Habilidad;
import com.practica.gestionempresa.service.HabilidadService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/habilidades")
public class HabilidadController {

    private final HabilidadService service;

    public HabilidadController(HabilidadService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Habilidad> crear(
            @Valid @RequestBody Habilidad habilidad) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.crear(habilidad));
    }

    @GetMapping
    public List<Habilidad> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Habilidad buscarPorId(
            @PathVariable Long id) {

        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Habilidad actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Habilidad habilidad) {

        return service.actualizar(
                id,
                habilidad);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {

        service.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}
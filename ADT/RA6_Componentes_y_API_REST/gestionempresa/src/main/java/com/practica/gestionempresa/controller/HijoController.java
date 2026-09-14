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

import com.practica.gestionempresa.model.Hijo;
import com.practica.gestionempresa.service.HijoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/hijos")
public class HijoController {

    private final HijoService service;

    public HijoController(HijoService service) {
        this.service = service;
    }

    @PostMapping("/empleado/{empleadoId}")
    public ResponseEntity<Hijo> crear(
            @PathVariable Long empleadoId,
            @Valid @RequestBody Hijo hijo) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.crear(
                        empleadoId,
                        hijo));
    }

    @GetMapping
    public List<Hijo> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Hijo buscarPorId(
            @PathVariable Long id) {

        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Hijo actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Hijo hijo) {

        return service.actualizar(id, hijo);
    }

    @PutMapping("/{hijoId}/empleado/{empleadoId}")
    public Hijo cambiarEmpleado(
            @PathVariable Long hijoId,
            @PathVariable Long empleadoId) {

        return service.cambiarEmpleado(
                hijoId,
                empleadoId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {

        service.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}
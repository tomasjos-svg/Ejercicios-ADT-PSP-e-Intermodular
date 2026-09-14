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

import com.practica.gestionempresa.model.Departamento;
import com.practica.gestionempresa.model.Empleado;
import com.practica.gestionempresa.service.DepartamentoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {

    private final DepartamentoService service;

    public DepartamentoController(DepartamentoService service) {
        this.service = service;
    }

    @PostMapping("/centro/{centroId}")
    public ResponseEntity<Departamento> crear(
            @PathVariable Long centroId,
            @Valid @RequestBody Departamento departamento) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.crear(
                        centroId,
                        departamento));
    }

    @GetMapping
    public List<Departamento> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Departamento buscarPorId(
            @PathVariable Long id) {

        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Departamento actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Departamento departamento) {

        return service.actualizar(
                id,
                departamento);
    }

    @PutMapping("/{departamentoId}/centro/{centroId}")
    public Departamento cambiarCentro(
            @PathVariable Long departamentoId,
            @PathVariable Long centroId) {

        return service.cambiarCentro(
                departamentoId,
                centroId);
    }

    @GetMapping("/{departamentoId}/empleados")
    public List<Empleado> listarEmpleados(
            @PathVariable Long departamentoId) {

        return service.listarEmpleados(departamentoId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {

        service.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}
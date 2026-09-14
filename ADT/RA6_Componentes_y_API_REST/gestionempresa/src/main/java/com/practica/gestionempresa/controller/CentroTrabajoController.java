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

import com.practica.gestionempresa.model.CentroTrabajo;
import com.practica.gestionempresa.model.Departamento;
import com.practica.gestionempresa.service.CentroTrabajoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/centros")
public class CentroTrabajoController {

    private final CentroTrabajoService service;

    public CentroTrabajoController(CentroTrabajoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CentroTrabajo> crear(
            @Valid @RequestBody CentroTrabajo centro) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.crear(centro));
    }

    @GetMapping
    public List<CentroTrabajo> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public CentroTrabajo buscarPorId(
            @PathVariable Long id) {

        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public CentroTrabajo actualizar(
            @PathVariable Long id,
            @Valid @RequestBody CentroTrabajo centro) {

        return service.actualizar(id, centro);
    }

    @PutMapping("/{centroId}/director/{empleadoId}")
    public CentroTrabajo asignarDirector(
            @PathVariable Long centroId,
            @PathVariable Long empleadoId) {

        return service.asignarDirector(
                centroId,
                empleadoId);
    }

    @DeleteMapping("/{centroId}/director")
    public CentroTrabajo quitarDirector(
            @PathVariable Long centroId) {

        return service.quitarDirector(centroId);
    }

    @GetMapping("/{centroId}/departamentos")
    public List<Departamento> listarDepartamentos(
            @PathVariable Long centroId) {

        return service.listarDepartamentos(centroId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {

        service.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}
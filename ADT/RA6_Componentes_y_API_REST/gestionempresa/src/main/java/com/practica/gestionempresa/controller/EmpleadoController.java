package com.practica.gestionempresa.controller;

import java.util.List;
import java.util.Set;

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

import com.practica.gestionempresa.model.Empleado;
import com.practica.gestionempresa.model.Habilidad;
import com.practica.gestionempresa.model.Hijo;
import com.practica.gestionempresa.service.EmpleadoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {

    private final EmpleadoService service;

    public EmpleadoController(EmpleadoService service) {
        this.service = service;
    }

    @PostMapping("/departamento/{departamentoId}")
    public ResponseEntity<Empleado> crear(
            @PathVariable Long departamentoId,
            @Valid @RequestBody Empleado empleado) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.crear(
                        departamentoId,
                        empleado));
    }

    @GetMapping
    public List<Empleado> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Empleado buscarPorId(
            @PathVariable Long id) {

        return service.buscarPorId(id);
    }
    @PutMapping("/{id}")
    public Empleado actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Empleado empleado) {
        return service.actualizar(id, empleado);
    }
    @PutMapping("/{empleadoId}/departamento/{departamentoId}")
    public Empleado cambiarDepartamento(
            @PathVariable Long empleadoId,
            @PathVariable Long departamentoId) {
        return service.cambiarDepartamento(
                empleadoId,
                departamentoId);
    }
    @PutMapping("/{empleadoId}/habilidades/{habilidadId}")
    public Empleado agregarHabilidad(
            @PathVariable Long empleadoId,
            @PathVariable Long habilidadId) {
        return service.agregarHabilidad(
                empleadoId,
                habilidadId);
    }
    @DeleteMapping("/{empleadoId}/habilidades/{habilidadId}")
    public Empleado quitarHabilidad(
            @PathVariable Long empleadoId,
            @PathVariable Long habilidadId) {
        return service.quitarHabilidad(
                empleadoId,
                habilidadId);
    }
    @GetMapping("/{empleadoId}/habilidades")
    public Set<Habilidad> listarHabilidades(
            @PathVariable Long empleadoId) {
        return service.listarHabilidades(empleadoId);
    }
    @GetMapping("/{empleadoId}/hijos")
    public List<Hijo> listarHijos(
            @PathVariable Long empleadoId) {
        return service.listarHijos(empleadoId);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/puesto/{texto}")
    public List<Empleado> buscarPorPuesto(@PathVariable String texto) {
        return service.buscarPorPuesto(texto);
    }

    @GetMapping("/departamento-nombre/{nombre}")
    public List<Empleado> buscarPorDepartamento(@PathVariable String nombre) {
        return service.buscarPorNombreDepartamento(nombre);
    }
    @GetMapping("/prueba-n1")
    public List<Empleado> pruebaN1() {
        return service.listarPruebaN1();
    }

    @GetMapping("/con-habilidades")
    public List<Empleado> conHabilidades() {
        return service.listarConHabilidades();
    }

}
package com.miempresa.app.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;
import com.miempresa.app.model.Empleado;
import com.miempresa.app.service.EmpleadoService;

@RestController
@RequestMapping("/api")
public class EmpleadoController {

    private final EmpleadoService empService;

    public EmpleadoController(EmpleadoService empService) {
        this.empService = empService;
    }

    @GetMapping("/empleados")
    public ResponseEntity<List<Empleado>> listarTodos() {
        return ResponseEntity.ok(empService.listarTodos());
    }

    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado> ver(@PathVariable Long id) {
        return ResponseEntity.ok(empService.obtenerPorId(id));
    }

    @GetMapping("/departamentos/{departamentoId}/empleados")
    public ResponseEntity<List<Empleado>> listarPorDept(@PathVariable Long departamentoId) {
        return ResponseEntity.ok(empService.listarPorDepartamento(departamentoId));
    }

    @PostMapping("/departamentos/{departamentoId}/empleados")
    public ResponseEntity<Empleado> crear(@PathVariable Long departamentoId,
                                          @Valid @RequestBody Empleado emp) {
        Empleado creado = empService.crearEnDepartamento(departamentoId, emp);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/empleados/{id}")
    public ResponseEntity<Empleado> actualizar(@PathVariable Long id,
                                               @Valid @RequestBody Empleado emp) {
        return ResponseEntity.ok(empService.actualizar(id, emp));
    }

    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        empService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

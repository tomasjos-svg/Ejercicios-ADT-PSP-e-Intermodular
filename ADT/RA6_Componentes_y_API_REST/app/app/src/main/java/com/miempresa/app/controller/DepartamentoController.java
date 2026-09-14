package com.miempresa.app.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;
import com.miempresa.app.model.Departamento;
import com.miempresa.app.service.DepartamentoService;

@RestController
@RequestMapping("/api/departamentos")
public class DepartamentoController {

    private final DepartamentoService deptService;

    public DepartamentoController(DepartamentoService deptService) {
        this.deptService = deptService;
    }

    @GetMapping
    public ResponseEntity<List<Departamento>> listar() {
        return ResponseEntity.ok(deptService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Departamento> ver(@PathVariable Long id) {
        return ResponseEntity.ok(deptService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<Departamento> crear(@Valid @RequestBody Departamento dept) {
        Departamento creado = deptService.crear(dept);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Departamento> actualizar(@PathVariable Long id,
                                                  @Valid @RequestBody Departamento dept) {
        return ResponseEntity.ok(deptService.actualizar(id, dept));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        deptService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

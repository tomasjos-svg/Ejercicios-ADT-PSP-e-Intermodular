package com.practica_5.gestion_biblioteca.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.practica_5.gestion_biblioteca.model.Prestamo;
import com.practica_5.gestion_biblioteca.service.PrestamoService;

import java.util.List;

@RestController
@RequestMapping("/prestamos")
public class PrestamoController {

    @Autowired
    private PrestamoService prestamoService;

    @PostMapping
    public ResponseEntity<Prestamo> crearPrestamo(
            @RequestBody Prestamo prestamo) {
        try {
            Prestamo creado = prestamoService.crearPrestamo(prestamo);
            return ResponseEntity.ok(creado);
        } catch (RuntimeException e) {
            // Error de negocio (libro no disponible, etc.)
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prestamo> obtenerPrestamo(
            @PathVariable String id) {
        return prestamoService.obtenerPrestamo(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Prestamo> listarPrestamos() {
        return prestamoService.listarPrestamos();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarPrestamo(
            @PathVariable String id) {
        try {
            prestamoService.borrarPrestamo(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
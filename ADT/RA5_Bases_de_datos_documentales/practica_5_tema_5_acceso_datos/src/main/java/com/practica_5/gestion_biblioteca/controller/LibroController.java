package com.practica_5.gestion_biblioteca.controller;

import com.practica_5.gestion_biblioteca.model.Libro;
import com.practica_5.gestion_biblioteca.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;

    @PostMapping
    public ResponseEntity<Libro> crearLibro(@RequestBody Libro libro) {
        Libro creado = libroService.crearLibro(libro);
        return ResponseEntity.ok(creado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtenerLibro(@PathVariable String id) {
        return libroService.obtenerLibro(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Libro> listarLibros() {
        return libroService.listarLibros();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizarLibro(
            @PathVariable String id,
            @RequestBody Libro libro) {
        try {
            Libro actualizado = libroService.actualizarLibro(id, libro);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            // Si no se encuentra el libro u ocurre error de negocio,
            // retornamos 404 o 400 según el caso
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarLibro(@PathVariable String id) {
        try {
            libroService.borrarLibro(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            // Si hay préstamo activo u otro error,
            // respondemos 400 Bad Request
            return ResponseEntity.badRequest().build();
        }
    }
}
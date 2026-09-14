package com.practica_5.gestion_biblioteca.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.practica_5.gestion_biblioteca.model.*;

import java.util.List;

public interface LibroRepository extends MongoRepository<Libro, String> {
    // Ejemplo de método derivado (opcional):
    // Show AOT-generated Implementation, Query, etc...
    List<Libro> findByTitulo(String titulo);
}
package com.practica_5.gestion_biblioteca.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.practica_5.gestion_biblioteca.model.Prestamo;

public interface PrestamoRepository extends MongoRepository<Prestamo, String> {

    List<Prestamo> findByLibroId(String libroId);

    List<Prestamo> findByUsuarioId(String usuarioId);
}
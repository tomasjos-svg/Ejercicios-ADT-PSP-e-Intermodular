package com.practica_5.gestion_biblioteca.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.practica_5.gestion_biblioteca.model.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    // Podríamos agregar consultas derivadas, e.g. findByEmail, si se requiere.
}
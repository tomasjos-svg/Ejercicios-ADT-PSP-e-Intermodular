package com.practica.gestionempresa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practica.gestionempresa.model.Habilidad;

public interface HabilidadRepository
        extends JpaRepository<Habilidad, Long> {

    Optional<Habilidad> findByNombre(String nombre);

    boolean existsByNombre(String nombre);
}
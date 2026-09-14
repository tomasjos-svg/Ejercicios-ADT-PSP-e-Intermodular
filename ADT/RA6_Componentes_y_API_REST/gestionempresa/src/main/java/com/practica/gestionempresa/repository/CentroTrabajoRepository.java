package com.practica.gestionempresa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practica.gestionempresa.model.CentroTrabajo;
import com.practica.gestionempresa.model.Empleado;

public interface CentroTrabajoRepository
        extends JpaRepository<CentroTrabajo, Long> {

    List<CentroTrabajo> findByDirector(Empleado director);

    boolean existsByDirector(Empleado director);
}
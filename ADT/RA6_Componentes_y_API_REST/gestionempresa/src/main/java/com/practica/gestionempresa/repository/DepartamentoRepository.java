package com.practica.gestionempresa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practica.gestionempresa.model.CentroTrabajo;
import com.practica.gestionempresa.model.Departamento;

public interface DepartamentoRepository
        extends JpaRepository<Departamento, Long> {

    List<Departamento> findByCentro(CentroTrabajo centro);

    boolean existsByCentro(CentroTrabajo centro);
}
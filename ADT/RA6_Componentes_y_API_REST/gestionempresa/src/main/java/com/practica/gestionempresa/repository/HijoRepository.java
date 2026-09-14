package com.practica.gestionempresa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practica.gestionempresa.model.Empleado;
import com.practica.gestionempresa.model.Hijo;

public interface HijoRepository extends JpaRepository<Hijo, Long> {

    List<Hijo> findByEmpleado(Empleado empleado);

    boolean existsByEmpleado(Empleado empleado);
}
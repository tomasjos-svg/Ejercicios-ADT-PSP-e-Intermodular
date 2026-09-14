package com.miempresa.app.repository;

import com.miempresa.app.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    List<Empleado> findByDepartamentoId(Long departamentoId);
    boolean existsByDepartamentoId(Long departamentoId);
}

package com.miempresa.app.repository;

import com.miempresa.app.model.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
    // No es necesario código adicional: Spring provee findAll(), findById(), save(), deleteById(), etc.
}

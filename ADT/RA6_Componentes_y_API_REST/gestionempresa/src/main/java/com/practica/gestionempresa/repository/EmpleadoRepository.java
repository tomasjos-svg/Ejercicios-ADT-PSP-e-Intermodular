package com.practica.gestionempresa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practica.gestionempresa.model.Departamento;
import com.practica.gestionempresa.model.Empleado;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmpleadoRepository
        extends JpaRepository<Empleado, Long> {

    List<Empleado> findByDepartamento(Departamento departamento);

    boolean existsByDepartamento(Departamento departamento);
    @Query("""
    	       SELECT e
    	       FROM Empleado e
    	       WHERE LOWER(e.puesto) LIKE LOWER(CONCAT('%', :texto, '%'))
    	       """)
    List<Empleado> buscarPorPuesto(@Param("texto") String texto);
    
    @Query("""
    	       SELECT e
    	       FROM Empleado e
    	       WHERE LOWER(e.departamento.nombre) = LOWER(:nombre)
    	       """)
    	List<Empleado> buscarPorNombreDepartamento(@Param("nombre") String nombre);
    
    @Query("""
    	       SELECT DISTINCT e
    	       FROM Empleado e
    	       LEFT JOIN FETCH e.habilidades
    	       """)
    	List<Empleado> findAllConHabilidades();

}

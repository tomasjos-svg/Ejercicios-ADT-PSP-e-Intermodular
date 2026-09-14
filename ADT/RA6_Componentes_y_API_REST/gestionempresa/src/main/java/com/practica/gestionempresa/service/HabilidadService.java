package com.practica.gestionempresa.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practica.gestionempresa.exception.OperacionNoPermitidaException;
import com.practica.gestionempresa.exception.RecursoNoEncontradoException;
import com.practica.gestionempresa.model.Habilidad;
import com.practica.gestionempresa.repository.HabilidadRepository;

@Service
@Transactional
public class HabilidadService {

    private final HabilidadRepository habilidadRepository;

    public HabilidadService(HabilidadRepository habilidadRepository) {
        this.habilidadRepository = habilidadRepository;
    }

    public Habilidad crear(Habilidad habilidad) {

        if (habilidadRepository.existsByNombre(habilidad.getNombre())) {
            throw new OperacionNoPermitidaException(
                "Ya existe una habilidad con el nombre "
                + habilidad.getNombre());
        }

        habilidad.setId(null);

        return habilidadRepository.save(habilidad);
    }

    @Transactional(readOnly = true)
    public List<Habilidad> listarTodos() {
        return habilidadRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Habilidad buscarPorId(Long id) {

        return habilidadRepository.findById(id)
                .orElseThrow(() ->
                    new RecursoNoEncontradoException(
                        "No existe la habilidad con id " + id));
    }

    public Habilidad actualizar(
            Long id,
            Habilidad datosNuevos) {

        Habilidad habilidad = buscarPorId(id);

        habilidad.setNombre(datosNuevos.getNombre());
        habilidad.setDescripcion(datosNuevos.getDescripcion());

        return habilidadRepository.save(habilidad);
    }

    public void eliminar(Long id) {

        Habilidad habilidad = buscarPorId(id);

        if (!habilidad.getEmpleados().isEmpty()) {
            throw new OperacionNoPermitidaException(
                "No se puede eliminar la habilidad porque esta asignada a empleados");
        }

        habilidadRepository.delete(habilidad);
    }
}
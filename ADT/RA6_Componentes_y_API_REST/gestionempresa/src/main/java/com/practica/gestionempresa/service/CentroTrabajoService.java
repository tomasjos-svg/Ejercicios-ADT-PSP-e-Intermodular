package com.practica.gestionempresa.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practica.gestionempresa.exception.OperacionNoPermitidaException;
import com.practica.gestionempresa.exception.RecursoNoEncontradoException;
import com.practica.gestionempresa.model.CentroTrabajo;
import com.practica.gestionempresa.model.Departamento;
import com.practica.gestionempresa.model.Empleado;
import com.practica.gestionempresa.repository.CentroTrabajoRepository;
import com.practica.gestionempresa.repository.DepartamentoRepository;
import com.practica.gestionempresa.repository.EmpleadoRepository;

@Service
@Transactional
public class CentroTrabajoService {

    private final CentroTrabajoRepository centroRepository;
    private final DepartamentoRepository departamentoRepository;
    private final EmpleadoRepository empleadoRepository;

    public CentroTrabajoService(
            CentroTrabajoRepository centroRepository,
            DepartamentoRepository departamentoRepository,
            EmpleadoRepository empleadoRepository) {

        this.centroRepository = centroRepository;
        this.departamentoRepository = departamentoRepository;
        this.empleadoRepository = empleadoRepository;
    }

    public CentroTrabajo crear(CentroTrabajo centro) {

        centro.setId(null);
        centro.setDirector(null);

        return centroRepository.save(centro);
    }

    @Transactional(readOnly = true)
    public List<CentroTrabajo> listarTodos() {
        return centroRepository.findAll();
    }

    @Transactional(readOnly = true)
    public CentroTrabajo buscarPorId(Long id) {

        return centroRepository.findById(id)
                .orElseThrow(() ->
                    new RecursoNoEncontradoException(
                        "No existe el centro de trabajo con id " + id));
    }

    public CentroTrabajo actualizar(
            Long id,
            CentroTrabajo datosNuevos) {

        CentroTrabajo centro = buscarPorId(id);
        centro.setNombre(datosNuevos.getNombre());
        centro.setDireccion(datosNuevos.getDireccion());
        return centroRepository.save(centro);
    }

    public CentroTrabajo asignarDirector(
            Long centroId,
            Long empleadoId) {
        CentroTrabajo centro = buscarPorId(centroId);
        Empleado empleado = empleadoRepository.findById(empleadoId)
                .orElseThrow(() ->
                    new RecursoNoEncontradoException(
                        "No existe el empleado con id " + empleadoId));
        centro.setDirector(empleado);
        return centroRepository.save(centro);
    }
    public CentroTrabajo quitarDirector(Long centroId) {
        CentroTrabajo centro = buscarPorId(centroId);
        centro.setDirector(null);
        return centroRepository.save(centro);
    }
    @Transactional(readOnly = true)
    public List<Departamento> listarDepartamentos(Long centroId) {
        CentroTrabajo centro = buscarPorId(centroId);
        return departamentoRepository.findByCentro(centro);
    }
    public void eliminar(Long id) {

        CentroTrabajo centro = buscarPorId(id);

        if (departamentoRepository.existsByCentro(centro)) {
            throw new OperacionNoPermitidaException(
                "No se puede eliminar el centro porque contiene departamentos");
        }

        centroRepository.delete(centro);
    }
}
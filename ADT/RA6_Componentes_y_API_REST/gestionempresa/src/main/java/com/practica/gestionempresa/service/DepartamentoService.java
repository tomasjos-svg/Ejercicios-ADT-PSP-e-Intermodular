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
public class DepartamentoService {

    private final DepartamentoRepository departamentoRepository;
    private final CentroTrabajoRepository centroRepository;
    private final EmpleadoRepository empleadoRepository;

    public DepartamentoService(
            DepartamentoRepository departamentoRepository,
            CentroTrabajoRepository centroRepository,
            EmpleadoRepository empleadoRepository) {

        this.departamentoRepository = departamentoRepository;
        this.centroRepository = centroRepository;
        this.empleadoRepository = empleadoRepository;
    }

    public Departamento crear(
            Long centroId,
            Departamento departamento) {

        CentroTrabajo centro = centroRepository.findById(centroId)
                .orElseThrow(() ->
                    new RecursoNoEncontradoException(
                        "No existe el centro de trabajo con id " + centroId));

        departamento.setId(null);
        departamento.setCentro(centro);

        return departamentoRepository.save(departamento);
    }

    @Transactional(readOnly = true)
    public List<Departamento> listarTodos() {
        return departamentoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Departamento buscarPorId(Long id) {

        return departamentoRepository.findById(id)
                .orElseThrow(() ->
                    new RecursoNoEncontradoException(
                        "No existe el departamento con id " + id));
    }

    public Departamento actualizar(
            Long id,
            Departamento datosNuevos) {

        Departamento departamento = buscarPorId(id);

        departamento.setNombre(datosNuevos.getNombre());

        return departamentoRepository.save(departamento);
    }

    public Departamento cambiarCentro(
            Long departamentoId,
            Long centroId) {

        Departamento departamento = buscarPorId(departamentoId);

        CentroTrabajo centro = centroRepository.findById(centroId)
                .orElseThrow(() ->
                    new RecursoNoEncontradoException(
                        "No existe el centro de trabajo con id " + centroId));

        departamento.setCentro(centro);

        return departamentoRepository.save(departamento);
    }
    @Transactional(readOnly = true)
    public List<Empleado> listarEmpleados(Long departamentoId) {

        Departamento departamento = buscarPorId(departamentoId);

        return empleadoRepository.findByDepartamento(departamento);
    }

    public void eliminar(Long id) {

        Departamento departamento = buscarPorId(id);

        if (empleadoRepository.existsByDepartamento(departamento)) {
            throw new OperacionNoPermitidaException(
                "No se puede eliminar el departamento porque contiene empleados");
        }

        departamentoRepository.delete(departamento);
    }
}
package com.practica.gestionempresa.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practica.gestionempresa.exception.RecursoNoEncontradoException;
import com.practica.gestionempresa.model.Empleado;
import com.practica.gestionempresa.model.Hijo;
import com.practica.gestionempresa.repository.EmpleadoRepository;
import com.practica.gestionempresa.repository.HijoRepository;

@Service
@Transactional
public class HijoService {

    private final HijoRepository hijoRepository;
    private final EmpleadoRepository empleadoRepository;

    public HijoService(
            HijoRepository hijoRepository,
            EmpleadoRepository empleadoRepository) {

        this.hijoRepository = hijoRepository;
        this.empleadoRepository = empleadoRepository;
    }

    public Hijo crear(
            Long empleadoId,
            Hijo hijo) {

        Empleado empleado =
                empleadoRepository.findById(empleadoId)
                .orElseThrow(() ->
                    new RecursoNoEncontradoException(
                        "No existe el empleado con id " + empleadoId));

        hijo.setId(null);
        hijo.setEmpleado(empleado);

        return hijoRepository.save(hijo);
    }

    @Transactional(readOnly = true)
    public List<Hijo> listarTodos() {
        return hijoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Hijo buscarPorId(Long id) {

        return hijoRepository.findById(id)
                .orElseThrow(() ->
                    new RecursoNoEncontradoException(
                        "No existe el hijo con id " + id));
    }

    public Hijo actualizar(
            Long id,
            Hijo datosNuevos) {

        Hijo hijo = buscarPorId(id);

        hijo.setNombre(datosNuevos.getNombre());
        hijo.setApellidos(datosNuevos.getApellidos());
        hijo.setDocumento(datosNuevos.getDocumento());
        hijo.setFechaNacimiento(datosNuevos.getFechaNacimiento());

        return hijoRepository.save(hijo);
    }

    public Hijo cambiarEmpleado(
            Long hijoId,
            Long empleadoId) {

        Hijo hijo = buscarPorId(hijoId);

        Empleado empleado =
                empleadoRepository.findById(empleadoId)
                .orElseThrow(() ->
                    new RecursoNoEncontradoException(
                        "No existe el empleado con id " + empleadoId));

        hijo.setEmpleado(empleado);

        return hijoRepository.save(hijo);
    }

    public void eliminar(Long id) {

        Hijo hijo = buscarPorId(id);

        hijoRepository.delete(hijo);
    }
}
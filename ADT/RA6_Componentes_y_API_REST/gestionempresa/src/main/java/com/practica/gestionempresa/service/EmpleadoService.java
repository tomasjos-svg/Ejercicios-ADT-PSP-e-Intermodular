package com.practica.gestionempresa.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practica.gestionempresa.exception.OperacionNoPermitidaException;
import com.practica.gestionempresa.exception.RecursoNoEncontradoException;
import com.practica.gestionempresa.model.Departamento;
import com.practica.gestionempresa.model.Empleado;
import com.practica.gestionempresa.model.Habilidad;
import com.practica.gestionempresa.model.Hijo;
import com.practica.gestionempresa.repository.CentroTrabajoRepository;
import com.practica.gestionempresa.repository.DepartamentoRepository;
import com.practica.gestionempresa.repository.EmpleadoRepository;
import com.practica.gestionempresa.repository.HabilidadRepository;
import com.practica.gestionempresa.repository.HijoRepository;

@Service
@Transactional
public class EmpleadoService {

    private final EmpleadoRepository empleadoRepository;
    private final DepartamentoRepository departamentoRepository;
    private final HijoRepository hijoRepository;
    private final HabilidadRepository habilidadRepository;
    private final CentroTrabajoRepository centroRepository;

    public EmpleadoService(
            EmpleadoRepository empleadoRepository,
            DepartamentoRepository departamentoRepository,
            HijoRepository hijoRepository,
            HabilidadRepository habilidadRepository,
            CentroTrabajoRepository centroRepository) {

        this.empleadoRepository = empleadoRepository;
        this.departamentoRepository = departamentoRepository;
        this.hijoRepository = hijoRepository;
        this.habilidadRepository = habilidadRepository;
        this.centroRepository = centroRepository;
    }

    public Empleado crear(
            Long departamentoId,
            Empleado empleado) {

        Departamento departamento =
                departamentoRepository.findById(departamentoId)
                .orElseThrow(() ->
                    new RecursoNoEncontradoException(
                        "No existe el departamento con id " + departamentoId));

        empleado.setId(null);
        empleado.setDepartamento(departamento);

        return empleadoRepository.save(empleado);
    }

    @Transactional(readOnly = true)
    public List<Empleado> listarTodos() {
        return empleadoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Empleado buscarPorId(Long id) {

        return empleadoRepository.findById(id)
                .orElseThrow(() ->
                    new RecursoNoEncontradoException(
                        "No existe el empleado con id " + id));
    }

    public Empleado actualizar(
            Long id,
            Empleado datosNuevos) {

        Empleado empleado = buscarPorId(id);

        empleado.setNombre(datosNuevos.getNombre());
        empleado.setApellidos(datosNuevos.getApellidos());
        empleado.setDocumento(datosNuevos.getDocumento());
        empleado.setPuesto(datosNuevos.getPuesto());
        empleado.setSalario(datosNuevos.getSalario());

        return empleadoRepository.save(empleado);
    }

    public Empleado cambiarDepartamento(
            Long empleadoId,
            Long departamentoId) {

        Empleado empleado = buscarPorId(empleadoId);

        Departamento departamento =
                departamentoRepository.findById(departamentoId)
                .orElseThrow(() ->
                    new RecursoNoEncontradoException(
                        "No existe el departamento con id " + departamentoId));

        empleado.setDepartamento(departamento);

        return empleadoRepository.save(empleado);
    }

    public Empleado agregarHabilidad(
            Long empleadoId,
            Long habilidadId) {

        Empleado empleado = buscarPorId(empleadoId);

        Habilidad habilidad =
                habilidadRepository.findById(habilidadId)
                .orElseThrow(() ->
                    new RecursoNoEncontradoException(
                        "No existe la habilidad con id " + habilidadId));

        empleado.getHabilidades().add(habilidad);

        return empleadoRepository.save(empleado);
    }

    public Empleado quitarHabilidad(
            Long empleadoId,
            Long habilidadId) {

        Empleado empleado = buscarPorId(empleadoId);

        Habilidad habilidad =
                habilidadRepository.findById(habilidadId)
                .orElseThrow(() ->
                    new RecursoNoEncontradoException(
                        "No existe la habilidad con id " + habilidadId));

        empleado.getHabilidades().remove(habilidad);

        return empleadoRepository.save(empleado);
    }

    @Transactional(readOnly = true)
    public Set<Habilidad> listarHabilidades(Long empleadoId) {

        Empleado empleado = buscarPorId(empleadoId);

        empleado.getHabilidades().size();

        return empleado.getHabilidades();
    }

    @Transactional(readOnly = true)
    public List<Hijo> listarHijos(Long empleadoId) {

        Empleado empleado = buscarPorId(empleadoId);

        return hijoRepository.findByEmpleado(empleado);
    }

    public void eliminar(Long id) {

        Empleado empleado = buscarPorId(id);

        if (hijoRepository.existsByEmpleado(empleado)) {
            throw new OperacionNoPermitidaException(
                "No se puede eliminar el empleado porque tiene hijos asociados");
        }

        if (centroRepository.existsByDirector(empleado)) {
            throw new OperacionNoPermitidaException(
                "No se puede eliminar el empleado porque dirige uno o varios centros");
        }

        empleado.getHabilidades().clear();

        empleadoRepository.save(empleado);

        empleadoRepository.delete(empleado);
    }
    
    public List<Empleado> buscarPorPuesto(String texto) {
        return empleadoRepository.buscarPorPuesto(texto);
    }

    public List<Empleado> buscarPorNombreDepartamento(String nombre) {
        return empleadoRepository.buscarPorNombreDepartamento(nombre);
    }
    
    @Transactional(readOnly = true)
    public List<Empleado> listarPruebaN1() {
        List<Empleado> empleados = empleadoRepository.findAll();

        for (Empleado empleado : empleados) {
            empleado.getHabilidades().size();
        }

        return empleados;
    }
    
    @Transactional(readOnly = true)
    public List<Empleado> listarConHabilidades() {
        return empleadoRepository.findAllConHabilidades();
    }

}
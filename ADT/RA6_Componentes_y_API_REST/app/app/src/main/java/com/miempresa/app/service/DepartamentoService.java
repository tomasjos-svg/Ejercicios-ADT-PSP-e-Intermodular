package com.miempresa.app.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import com.miempresa.app.model.Departamento;
import com.miempresa.app.repository.DepartamentoRepository;
import com.miempresa.app.repository.EmpleadoRepository;

@Service
public class DepartamentoService {

    private final DepartamentoRepository deptRepo;
    private final EmpleadoRepository empRepo;

    public DepartamentoService(DepartamentoRepository deptRepo, EmpleadoRepository empRepo) {
        this.deptRepo = deptRepo;
        this.empRepo = empRepo;
    }

    public List<Departamento> listarTodos() {
        return deptRepo.findAll();
    }

    public Departamento obtenerPorId(Long id) {
        return deptRepo.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Departamento no encontrado")
        );
    }

    public Departamento crear(Departamento dept) {
        dept.setId(null);
        return deptRepo.save(dept);
    }

    public Departamento actualizar(Long id, Departamento datos) {
        Departamento existente = obtenerPorId(id);
        existente.setNombre(datos.getNombre());
        return deptRepo.save(existente);
    }

    public void eliminar(Long id) {
        // Verificar si hay empleados en el departamento
        if (empRepo.existsByDepartamentoId(id)) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "No se puede eliminar: el departamento tiene empleados asociados"
            );
        }
        Departamento existente = obtenerPorId(id);
        deptRepo.delete(existente);
    }
}

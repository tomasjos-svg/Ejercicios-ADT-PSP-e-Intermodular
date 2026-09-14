package com.miempresa.app.service;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import com.miempresa.app.model.Empleado;
import com.miempresa.app.model.Departamento;
import com.miempresa.app.repository.EmpleadoRepository;
import com.miempresa.app.repository.DepartamentoRepository;
@Service
public class EmpleadoService {
    private final EmpleadoRepository empRepo;
    private final DepartamentoRepository deptRepo;
    public EmpleadoService(EmpleadoRepository empRepo, DepartamentoRepository deptRepo) {
        this.empRepo = empRepo;
        this.deptRepo = deptRepo;
    }
    public List<Empleado> listarTodos() {  return empRepo.findAll(); }
    public Empleado obtenerPorId(Long id) {
        return empRepo.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empleado no encontrado")
        );
    }
    public List<Empleado> listarPorDepartamento(Long deptId) {
        if (!deptRepo.existsById(deptId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Departamento no encontrado");
        }
        return empRepo.findByDepartamentoId(deptId);
    }
    public Empleado crearEnDepartamento(Long deptId, Empleado empleado) {
        Departamento dept = deptRepo.findById(deptId).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Departamento no encontrado")
        );
        empleado.setId(null);
        empleado.setDepartamento(dept);
        return empRepo.save(empleado);
    }
    public Empleado actualizar(Long id, Empleado datos) {
        Empleado existente = obtenerPorId(id);
        existente.setNombre(datos.getNombre());
        existente.setApellido(datos.getApellido());
        existente.setSalario(datos.getSalario());
        // Cambiar departamento si se proporciona
        if (datos.getDepartamento() != null && datos.getDepartamento().getId() != null) {
            Departamento nuevoDept = deptRepo.findById(datos.getDepartamento().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Departamento no encontrado"));
            existente.setDepartamento(nuevoDept);
        }
        return empRepo.save(existente);
    }
    public void eliminar(Long id) {
        Empleado existente = obtenerPorId(id);
        empRepo.delete(existente);
    }
}

package com.practica_5.gestion_biblioteca.service;

import com.practica_5.gestion_biblioteca.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.practica_5.gestion_biblioteca.model.Libro;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepo;

    @Autowired
    private PrestamoRepository prestamoRepo;

    public Libro crearLibro(Libro libro) {
        // Por regla de negocio, forzamos que un nuevo libro esté disponible.
        libro.setDisponible(true);
        return libroRepo.save(libro);
    }

    public Optional<Libro> obtenerLibro(String id) {
        return libroRepo.findById(id);
    }

    public List<Libro> listarLibros() {
        return libroRepo.findAll();
    }

    public Libro actualizarLibro(String id, Libro datosLibro) {
        // Buscar el libro existente
        Optional<Libro> opt = libroRepo.findById(id);

        if (opt.isEmpty()) {
            throw new RuntimeException("Libro no encontrado con id: " + id);
        }

        // Si existe, actualizar sus campos (excepto id)
        Libro libroExistente = opt.get();
        libroExistente.setTitulo(datosLibro.getTitulo());
        libroExistente.setAutor(datosLibro.getAutor());

        // Mantener el estado "disponible" del existente, a menos que decidamos cambiarlo manualmente.
        libroExistente.setDisponible(datosLibro.isDisponible());

        return libroRepo.save(libroExistente);
    }

    public void borrarLibro(String id) {
        // Regla de negocio adicional (opcional): no borrar libro si tiene un préstamo activo.
        List<?> prestamos = prestamoRepo.findByLibroId(id);

        if (!prestamos.isEmpty()) {
            throw new RuntimeException("No se puede borrar: el libro está prestado actualmente");
        }

        libroRepo.deleteById(id);
    }
}
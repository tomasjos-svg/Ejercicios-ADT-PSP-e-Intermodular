package com.practica_5.gestion_biblioteca.service;

import com.practica_5.gestion_biblioteca.model.*;
import com.practica_5.gestion_biblioteca.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practica_5.gestion_biblioteca.model.Prestamo;
import com.practica_5.gestion_biblioteca.repository.PrestamoRepository;
import com.practica_5.gestion_biblioteca.repository.UsuarioRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PrestamoService {

    @Autowired
    private PrestamoRepository prestamoRepo;

    @Autowired
    private LibroRepository libroRepo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    public Prestamo crearPrestamo(Prestamo prestamo) {

        // Verificar existencia del usuario
        String usuarioId = prestamo.getUsuarioId();
        String libroId = prestamo.getLibroId();

        if (!usuarioRepo.existsById(usuarioId)) {
            throw new RuntimeException("No existe usuario con id: " + usuarioId);
        }

        // Verificar existencia del libro
        Optional<Libro> libroOpt = libroRepo.findById(libroId);

        if (libroOpt.isEmpty()) {
            throw new RuntimeException("No existe libro con id: " + libroId);
        }

        Libro libro = libroOpt.get();

        // Regla de negocio: solo permitir préstamo si el libro está disponible
        if (!libro.isDisponible()) {
            throw new RuntimeException("El libro ya está prestado/no disponible");
        }

        // Marcar el libro como no disponible y guardar el cambio
        libro.setDisponible(false);
        libroRepo.save(libro);

        // Establecer la fecha de préstamo al momento actual
        prestamo.setFechaPrestamo(new Date());

        // Guardar el préstamo
        return prestamoRepo.save(prestamo);
    }

    public Optional<Prestamo> obtenerPrestamo(String id) {
        return prestamoRepo.findById(id);
    }

    public List<Prestamo> listarPrestamos() {
        return prestamoRepo.findAll();
    }

    public void borrarPrestamo(String id) {
        // Al borrar un préstamo, marcamos el libro como disponible nuevamente
        Optional<Prestamo> prestamoOpt = prestamoRepo.findById(id);

        if (prestamoOpt.isEmpty()) {
            throw new RuntimeException("Préstamo no encontrado con id: " + id);
        }

        Prestamo prestamo = prestamoOpt.get();

        // Obtener el libro y marcarlo disponible
        String libroId = prestamo.getLibroId();

        libroRepo.findById(libroId).ifPresent(libro -> {
            libro.setDisponible(true);
            libroRepo.save(libro);
        });

        // Eliminar el registro de préstamo
        prestamoRepo.deleteById(id);
    }
}
package com.practica_5.gestion_biblioteca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practica_5.gestion_biblioteca.model.Usuario;
import com.practica_5.gestion_biblioteca.repository.PrestamoRepository;
import com.practica_5.gestion_biblioteca.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private PrestamoRepository prestamoRepo;

    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepo.save(usuario);
    }

    public Optional<Usuario> obtenerUsuario(String id) {
        return usuarioRepo.findById(id);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepo.findAll();
    }

    public Usuario actualizarUsuario(String id, Usuario datosUsuario) {
        Optional<Usuario> opt = usuarioRepo.findById(id);

        if (opt.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado con id: " + id);
        }

        Usuario usuarioExistente = opt.get();
        usuarioExistente.setNombre(datosUsuario.getNombre());
        usuarioExistente.setEmail(datosUsuario.getEmail());

        return usuarioRepo.save(usuarioExistente);
    }

    public void borrarUsuario(String id) {
        // Regla de negocio (opcional): evitar borrar usuario con préstamos activos.
        List<?> prestamos = prestamoRepo.findByUsuarioId(id);

        if (!prestamos.isEmpty()) {
            throw new RuntimeException("No se puede borrar: el usuario tiene préstamos activos");
        }

        usuarioRepo.deleteById(id);
    }
}
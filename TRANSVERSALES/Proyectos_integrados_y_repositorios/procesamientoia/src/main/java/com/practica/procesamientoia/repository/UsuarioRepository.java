package com.practica.procesamientoia.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practica.procesamientoia.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsername(String username);

}


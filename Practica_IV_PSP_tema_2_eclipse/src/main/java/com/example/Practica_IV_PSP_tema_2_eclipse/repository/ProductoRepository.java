package com.example.Practica_IV_PSP_tema_2_eclipse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Practica_IV_PSP_tema_2_eclipse.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    // no es necesario agregar métodos, JpaRepository ya provee métodos básicos
}
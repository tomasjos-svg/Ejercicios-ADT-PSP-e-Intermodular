package com.example.Practica_IV_PSP_tema_2_eclipse.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Practica_IV_PSP_tema_2_eclipse.model.Producto;
import com.example.Practica_IV_PSP_tema_2_eclipse.repository.ProductoRepository;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepo;
/*
    @GetMapping
    public List<Producto> listarProductos() {
        return productoRepo.findAll();
    }
*/
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProducto(@PathVariable Long id) {
        return productoRepo.findById(id)
                .map(prod -> ResponseEntity.ok(prod))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto nuevoProducto) {
        Producto prodGuardado = productoRepo.save(nuevoProducto);
        return ResponseEntity.status(HttpStatus.CREATED).body(prodGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(
            @PathVariable Long id,
            @RequestBody Producto prodDetalles) {

        return productoRepo.findById(id).map(prod -> {
            prod.setNombre(prodDetalles.getNombre());
            prod.setPrecio(prodDetalles.getPrecio());

            productoRepo.save(prod);

            return ResponseEntity.ok(prod);
        }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        if (productoRepo.existsById(id)) {
            productoRepo.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    @GetMapping
    public List<Producto> listarProductos() {
        String hilo = Thread.currentThread().getName();
        long inicio = System.currentTimeMillis();

        List<Producto> datos = productoRepo.findAll();

        long duracion = System.currentTimeMillis() - inicio;
        System.out.println("Hilo: " + hilo + " - " + duracion + " ms");
        return datos;
    }

}
package com.example.Practica_IV_PSP_tema_2_eclipse_cliente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.Practica_IV_tema_2_eclipse_cliente.model.Producto;

@SpringBootApplication
public class PracticaIvPspTema2EclipseClienteApplication {

	public static void main(String[] args) {
        SpringApplication.run(PracticaIvPspTema2EclipseClienteApplication.class, args);

        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8081/productos";

        // Hacer una petición GET que retorne una lista de Productos
        ResponseEntity<Producto[]> response =
                restTemplate.getForEntity(url, Producto[].class);

        if (response.getStatusCode() == HttpStatus.OK) {
            Producto[] productos = response.getBody();

            System.out.println("Número de productos: " + productos.length);

            for (Producto p : productos) {
                System.out.println(p.getId() + " - " + p.getNombre());
            }

        } else {
            System.out.println("Error, código: " + response.getStatusCode());
        }
    }

}







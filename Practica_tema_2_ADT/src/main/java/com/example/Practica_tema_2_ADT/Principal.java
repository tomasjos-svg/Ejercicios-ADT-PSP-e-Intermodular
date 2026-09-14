package com.example.Practica_tema_2_ADT;

import java.nio.file.Path;

public class Principal {

    public static void main(String[] args) {
        DatosTienda datos = new DatosTienda();
        try {
            // Cargar los tres ficheros
            datos.leerClientes(Path.of("data/clientes.csv"));
            datos.leerProductos(Path.of("data/productos.tsv"));
            datos.leerPedidos(Path.of("data/pedidos.psv"));
            System.out.println(
                    "Clientes cargados: "
                    + datos.getClientes().size());
            System.out.println(
                    "Productos cargados: "
                    + datos.getProductos().size());
            System.out.println(
                    "Pedidos válidos: "
                    + datos.getPedidos().size());
            // Generar los tres ficheros de salida
            datos.generarCsv("data/pedidos_consolidado.csv");
            datos.generarBinario("data/pedidos.labdat");
            datos.serializar("data/datos_tienda.ser");
            System.out.println(
                    "Ficheros generados correctamente.");
        } catch (Exception e) {
            System.out.println(
                    "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
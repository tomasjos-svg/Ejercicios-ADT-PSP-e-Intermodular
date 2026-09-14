package com.example.Practica_tema_2_ADT;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;

import java.nio.file.Files;
import java.nio.file.Path;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DatosTienda implements Serializable {
    private static final long serialVersionUID = 1L;
    private HashMap<String, Cliente> clientes = new HashMap<>();
    private HashMap<String, Producto> productos = new HashMap<>();
    private List<Pedido> pedidos = new ArrayList<>();
    // Constructor vacío
    public DatosTienda() {
    }
    public DatosTienda(HashMap<String, Cliente> clientes,
                       HashMap<String, Producto> productos,
                       List<Pedido> pedidos) {

        this.clientes = clientes;
        this.productos = productos;
        this.pedidos = pedidos;
    }
    // --------------------------------------------------
    // LECTURA DE CLIENTES
    // --------------------------------------------------
    public void leerClientes(Path ruta) throws IOException {
        List<String> lineas = Files.readAllLines(ruta);
        for (String linea : lineas) {
        	if (!linea.isBlank() && !linea.startsWith("#")) {
                String[] campos = linea.split(",");
                if (campos.length == 3) {
                	String id = campos[0].trim();
                    String nombre = campos[1].trim();
                    String apellidos = campos[2].trim();
                    Cliente cliente =
                            new Cliente(id, nombre, apellidos);
                    clientes.put(id, cliente);
                }
            }
        }
    }

    // --------------------------------------------------
    // LECTURA DE PRODUCTOS
    // --------------------------------------------------

    public void leerProductos(Path ruta) throws IOException {
        List<String> lineas = Files.readAllLines(ruta);
        for (String linea : lineas) {
            if (!linea.isBlank() && !linea.startsWith("#")) {
                String[] campos = linea.split("\t");
                if (campos.length == 3) {
                    String id = campos[0].trim();
                    String nombre = campos[1].trim();
                    double precio =
                            Double.parseDouble(campos[2].trim());
                    Producto producto =
                            new Producto(id, nombre, precio);
                    productos.put(id, producto);
                }
            }
        }
    }

    // --------------------------------------------------
    // LECTURA DE PEDIDOS
    // --------------------------------------------------

    public void leerPedidos(Path ruta) throws IOException {
        List<String> lineas = Files.readAllLines(ruta);
        for (String linea : lineas) {
            if (!linea.isBlank() && !linea.startsWith("#")) {
                String[] campos = linea.split("\\|");
                if (campos.length == 4) {
                    String id = campos[0].trim();
                    String idCliente = campos[1].trim();
                    String idProducto = campos[2].trim();
                    int cantidad =
                            Integer.parseInt(campos[3].trim());
                    /*
                     * Solo aceptamos el pedido si existen
                     * previamente el cliente y el producto.
                     */
                    if (clientes.containsKey(idCliente)
                            && productos.containsKey(idProducto)) {
                        Pedido pedido =
                                new Pedido(id,
                                           idCliente,
                                           idProducto,
                                           cantidad);
                        pedidos.add(pedido);
                    } else {
                        System.out.println(
                                "Pedido " + id
                                + " rechazado: cliente o producto inexistente");
                    }
                }
            }
        }
    }

    // --------------------------------------------------
    // GENERACIÓN DEL CSV CONSOLIDADO
    // --------------------------------------------------

    public void generarCsv(String nombreFichero)
            throws IOException {
        try (PrintWriter out =
                new PrintWriter(nombreFichero)) {
            out.println(
                    "id,cliente,producto,cantidad,total");
            for (Pedido pedido : pedidos) {
                Cliente cliente =
                        clientes.get(pedido.getIdCliente());
                Producto producto =
                        productos.get(pedido.getIdProducto());
                double total =
                        producto.getPrecio()
                        * pedido.getCantidad();
                out.println(
                        pedido.getId() + ","
                        + cliente.getNombre() + ","
                        + producto.getNombre() + ","
                        + pedido.getCantidad() + ","
                        + total);
            }
        }
    }

    // --------------------------------------------------
    // GENERACIÓN DEL FICHERO BINARIO
    // --------------------------------------------------
    public void generarBinario(String nombreFichero)
            throws IOException {
        try (DataOutputStream out =
                new DataOutputStream(
                    new FileOutputStream(nombreFichero))) {
            // Número de registros
            out.writeInt(pedidos.size());
            for (Pedido pedido : pedidos) {
                Cliente cliente =
                        clientes.get(pedido.getIdCliente());
                Producto producto =
                        productos.get(pedido.getIdProducto());
                double total =
                        producto.getPrecio()
                        * pedido.getCantidad();
                out.writeUTF(pedido.getId());
                out.writeUTF(cliente.getNombre());
                out.writeUTF(producto.getNombre());
                out.writeInt(pedido.getCantidad());
                out.writeDouble(total);
            }
        }
    }

    // --------------------------------------------------
    // SERIALIZACIÓN DE DATOSTIENDA
    // --------------------------------------------------

    public void serializar(String nombreFichero)
            throws IOException {
        try (ObjectOutputStream out =
                new ObjectOutputStream(
                    new FileOutputStream(nombreFichero))) {
            out.writeObject(this);
        }
    }

    // --------------------------------------------------
    // GETTERS Y SETTERS
    // --------------------------------------------------

    public HashMap<String, Cliente> getClientes() {
        return clientes;
    }
    public void setClientes(
            HashMap<String, Cliente> clientes) {
        this.clientes = clientes;
    }
    public HashMap<String, Producto> getProductos() {
        return productos;
    }
    public void setProductos(
            HashMap<String, Producto> productos) {
        this.productos = productos;
    }
    public List<Pedido> getPedidos() {
        return pedidos;
    }
    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}

package com.ejercicio14.concesionario.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "COCHE")
public class Coche implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "MATRICULA", length = 10)
    private String matricula;

    @Column(name = "MARCA", nullable = false, length = 50)
    private String marca;

    @Column(name = "MODELO", nullable = false, length = 50)
    private String modelo;

    @Column(name = "COLOR", length = 30)
    private String color;

    @Column(name = "PRECIO_VENTA",
            nullable = false, precision = 10, scale = 2)
    private BigDecimal precioVenta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CODIGO_CLIENTE")
    private Cliente cliente;

    @OneToMany(mappedBy = "coche")
    private List<Revision> revisiones = new ArrayList<>();

    public Coche() {
    }

    public Coche(String matricula, String marca, String modelo,
                 String color, BigDecimal precioVenta) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.precioVenta = precioVenta;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Revision> getRevisiones() {
        return revisiones;
    }

    @Override
    public String toString() {
        return "Coche{matricula='" + matricula + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", color='" + color + '\'' +
                ", precioVenta=" + precioVenta +
                '}';
    }
}
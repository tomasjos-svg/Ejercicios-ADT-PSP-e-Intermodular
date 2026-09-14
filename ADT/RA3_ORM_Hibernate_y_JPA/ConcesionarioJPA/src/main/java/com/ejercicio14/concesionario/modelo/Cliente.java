package com.ejercicio14.concesionario.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "CLIENTE")
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO_CLIENTE")
    private Long codigoCliente;

    @Column(name = "NIF", nullable = false, unique = true, length = 9)
    private String nif;

    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "DIRECCION", length = 150)
    private String direccion;

    @Column(name = "CIUDAD", length = 80)
    private String ciudad;

    @Column(name = "TELEFONO", length = 20)
    private String telefono;

    @OneToMany(mappedBy = "cliente")
    private List<Coche> coches = new ArrayList<>();

    public Cliente() {
    }

    public Cliente(String nif, String nombre, String direccion,
                   String ciudad, String telefono) {
        this.nif = nif;
        this.nombre = nombre;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.telefono = telefono;
    }

    public Long getCodigoCliente() {
        return codigoCliente;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<Coche> getCoches() {
        return coches;
    }

    public void setCoches(List<Coche> coches) {
        this.coches = coches;
    }

    @Override
    public String toString() {
        return "Cliente{codigoCliente=" + codigoCliente +
                ", nif='" + nif + '\'' +
                ", nombre='" + nombre + '\'' +
                ", ciudad='" + ciudad + '\'' +
                '}';
    }
}
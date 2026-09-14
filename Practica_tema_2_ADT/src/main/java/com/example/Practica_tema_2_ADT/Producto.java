package com.example.Practica_tema_2_ADT;

import java.io.Serializable;

public class Producto implements Serializable {
		private static final long serialVersionUID = 1L;
	private String id;
    private String nombre;
    private double precio;
	public Producto(String id, String nombre, double precio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
    // constructor, getters y setters
}

package com.example.Practica_tema_2_ADT;

import java.io.Serializable;

public class Pedido implements Serializable {
   
	private String id;
    private String idCliente;
    private String idProducto;
    private int cantidad;
    // constructor, getters y setters
    public Pedido(String id, String idCliente, String idProducto, int cantidad) {
		super();
		this.id = id;
		this.idCliente = idCliente;
		this.idProducto = idProducto;
		this.cantidad = cantidad;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}
	public String getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(String idProducto) {
		this.idProducto = idProducto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}

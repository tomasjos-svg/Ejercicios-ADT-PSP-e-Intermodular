package com.practica.Practica_VII_Tema_3_ADT_gestion_alumnos.model;

public class Direccion {
    private String calle;
    private int numero;
    private String codigoPostal;

    public Direccion(String calle, int numero, String codigoPostal) {
        this.calle = calle;
        this.numero = numero;
        this.codigoPostal = codigoPostal;
    }

    public String getCalle() { return calle; }
    public void setCalle(String calle) { this.calle = calle; }

    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }

    public String getCodigoPostal() { return codigoPostal; }
    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    @Override
    public String toString() {
        return calle + ", " + numero + " (" + codigoPostal + ")";
    }
}

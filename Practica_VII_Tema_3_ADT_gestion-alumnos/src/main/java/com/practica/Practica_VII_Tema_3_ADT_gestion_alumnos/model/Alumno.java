package com.practica.Practica_VII_Tema_3_ADT_gestion_alumnos.model;

public class Alumno {
    private String clave;
    private String nombre;
    private String apellidos;
    private int edad;
    private Direccion direccion;
    public Alumno(String clave, String nombre, String apellidos,
                  int edad, Direccion direccion) {
        this.clave = clave;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.direccion = direccion;
    }
    public String getClave() { return clave; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }
    public Direccion getDireccion() { return direccion; }
    public void setDireccion(Direccion direccion) { this.direccion = direccion; }
    @Override
    public String toString() {
        return clave + " - " + nombre + " " + apellidos
             + " - " + edad + " años - " + direccion;
    }
}

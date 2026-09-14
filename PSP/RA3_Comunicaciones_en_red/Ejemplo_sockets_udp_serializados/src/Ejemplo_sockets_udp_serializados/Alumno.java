package Ejemplo_sockets_udp_serializados;

import java.io.Serializable;

public class Alumno implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombre;
    private String apellidos;
    private int edad;

    public Alumno(String nombre, String apellidos, int edad) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
    }

    @Override
    public String toString() {
        return nombre + " " + apellidos + " - " + edad + " años";
    }
}

package Ejercicio_21_sockets_TCP;

import java.io.Serializable;

public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombre;
    private String contraseña;
    private String DNI;

    public Usuario(String nombre, String contraseña, String DNI) {
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.DNI = DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getDNI() {
        return DNI;
    }
}
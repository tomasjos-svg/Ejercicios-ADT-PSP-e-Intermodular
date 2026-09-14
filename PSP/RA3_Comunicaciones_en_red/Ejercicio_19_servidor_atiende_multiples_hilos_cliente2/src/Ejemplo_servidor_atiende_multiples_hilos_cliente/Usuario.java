package Ejemplo_servidor_atiende_multiples_hilos_cliente;

import java.io.Serializable;

public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombre;
    private String contraseña;
    private String permisos;

    public Usuario(String nombre, String contraseña, String permisos) {
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.permisos = permisos;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getPermisos() {
        return permisos;
    }
}
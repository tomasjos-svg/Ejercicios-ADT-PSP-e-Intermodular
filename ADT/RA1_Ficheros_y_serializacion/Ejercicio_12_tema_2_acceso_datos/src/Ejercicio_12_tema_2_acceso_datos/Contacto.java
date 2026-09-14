package Ejercicio_12_tema_2_acceso_datos;

public class Contacto {

    private String dni;
    private String nombre;
    private String telefono;

    public Contacto(String dni, String nombre, String telefono) {
        this.dni = dni;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    @Override
    public String toString() {
        return "DNI: " + dni
                + " Nombre: " + nombre
                + " Teléfono: " + telefono;
    }
}
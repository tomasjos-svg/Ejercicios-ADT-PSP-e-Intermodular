package Ejemplo_19_tema_2_acceso_datos;

import com.fasterxml.jackson.annotation.JsonProperty;
public class Persona {
    @JsonProperty("nombre")
    private String nombreReal;
    private String apellidos;
    private int edad;
    public Persona() {
    }
    public Persona(
            String nombreReal,
            String apellidos,
            int edad) {
        this.nombreReal = nombreReal;
        this.apellidos = apellidos;
        this.edad = edad;
    }
    public String getNombreReal() {
        return nombreReal;
    }
    public void setNombreReal(String nombreReal) {
        this.nombreReal = nombreReal;
    }
    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }
}
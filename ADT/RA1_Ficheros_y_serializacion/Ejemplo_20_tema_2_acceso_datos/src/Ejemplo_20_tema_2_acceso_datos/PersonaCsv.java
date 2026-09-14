package Ejemplo_20_tema_2_acceso_datos;

import com.opencsv.bean.CsvBindByName;
public class PersonaCsv {
    @CsvBindByName(column = "Nombre")
    private String nombre;
    @CsvBindByName(column = "Edad")
    private int edad;
    public PersonaCsv() {
    }
    public PersonaCsv(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getEdad() {
        return edad;
    }
    public void setEdad(int edad) {
        this.edad = edad;
    }
    @Override
    public String toString() {
        return "PersonaCsv [nombre=" + nombre + ", edad=" + edad + "]";
    }
}
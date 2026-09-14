package Ejemplo_11_tema_2_acceso_datos;

import java.io.Serializable;

public class Coche implements Serializable {

    private static final long serialVersionUID = 1L;

    private String matricula;
    private String marca;
    private int cilindrada;

    public Coche(String matricula, String marca, int cilindrada) {
        this.matricula = matricula;
        this.marca = marca;
        this.cilindrada = cilindrada;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getMarca() {
        return marca;
    }

    public int getCilindrada() {
        return cilindrada;
    }
}
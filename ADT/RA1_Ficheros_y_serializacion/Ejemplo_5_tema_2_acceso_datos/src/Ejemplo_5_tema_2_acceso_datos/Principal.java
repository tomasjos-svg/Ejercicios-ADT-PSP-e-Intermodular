package Ejemplo_5_tema_2_acceso_datos;

public class Principal {
    public static void main(String[] args) {
        Persona p=new Persona();
        try {
            p.escribeEdad(102);
        }
        catch (excepcionEdad e) {
            System.out.println(e.getMessage());
        }
    }
}

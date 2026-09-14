package Ejemplo_1_tema_2_acceso_datos;

import java.io.File;
public class Ejemplo {
    public static void main(String[] args) {        
        File directorio = new File(".\\prueba");
        if (directorio.mkdir()) {
            System.out.println("Directorio creado");
        }
        else {
            System.out.print("Directorio no creado. ");
            if (directorio.exists()) 
                System.out.println(": directorio ya existe");
            else 
                System.out.println("Razón desconocida");
        }        
      }       
    }    

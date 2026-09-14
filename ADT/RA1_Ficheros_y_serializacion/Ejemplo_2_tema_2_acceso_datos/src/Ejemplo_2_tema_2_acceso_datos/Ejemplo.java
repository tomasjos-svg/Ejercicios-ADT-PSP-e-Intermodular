package Ejemplo_2_tema_2_acceso_datos;

import java.io.File;
import java.io.IOException;
public class Ejemplo {
    public static void main(String[] args) {
    	File directorio_prueba=new File(".\\prueba");
        File fichero = new File(directorio_prueba,"datos.txt");
        directorio_prueba.mkdir();
        try {
			fichero.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if (fichero.delete()) {
            System.out.println("Fichero eliminado");
        }
        else {
            System.out.print("Fichero no eliminado. ");
            if (!fichero.exists()) 
                   System.out.println("Razón: fichero no existe. ");
            else 
                  System.out.println("Razón desconocida. ");
        }        
        File directorio = new File(".\\maniobra");
        directorio.mkdir();
        if (directorio.delete()) {
            System.out.println("Directorio eliminado");
        }
        else {
            System.out.print("Directorio no eliminado. ");
            if (!directorio.exists()) System.out.println("Razón: directorio no existe. ");
            else System.out.println("Razón desconocida. ");
        }
    }    
}

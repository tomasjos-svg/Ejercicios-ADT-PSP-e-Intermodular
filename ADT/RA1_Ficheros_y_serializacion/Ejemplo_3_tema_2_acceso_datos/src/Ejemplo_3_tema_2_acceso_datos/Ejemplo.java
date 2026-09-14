package Ejemplo_3_tema_2_acceso_datos;

import java.io.File;
import java.io.IOException;
public class Ejemplo {
    public static void main(String[] args) throws IOException {
        System.out.println("Renombrar el directorio .\\maniobra con el nombre .\\prueba");
        File directorioViejo = new File(".\\maniobra");
        directorioViejo.mkdir();
        if (directorioViejo.exists())
        	System.out.println("directorio creado");
        else
        	System.out.println("directorio no creado");
        File directorioNuevo = new File(".\\prueba");  
        
        if(directorioViejo.renameTo(directorioNuevo)) 
              System.out.println("Directorio renombrado");
        else 
             System.out.println("El directorio no ha podido ser renombrado. ");        
        System.out.println("enombrar el fichero .\\gestion\\datos.txt con el nombre .\\gestion\\nuevo.txt");
        File directorio = new File(".\\gestion");
        directorio.mkdir();
        File ficheroViejo = new File(directorio,"datos.txt");
        ficheroViejo.createNewFile();
        File ficheroNuevo = new File(directorio,"nuevo.txt");        
        if (ficheroViejo.renameTo(ficheroNuevo)) 
            System.out.println("Fichero renombrado");
        else System.out.println("El fichero no ha podido ser renombrado. ");        
    }    
}

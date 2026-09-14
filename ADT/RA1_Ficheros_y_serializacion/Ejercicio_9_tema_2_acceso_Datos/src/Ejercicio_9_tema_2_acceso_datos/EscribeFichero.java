package Ejercicio_9_tema_2_acceso_datos;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
public class EscribeFichero {
  public static void main(String args[]) throws IOException {
      FileWriter out = null;
      Scanner sc=new Scanner(System.in);
      try {
    	  File directorio_prueba=new File(".\\alumnos");
          File fichero = new File(directorio_prueba,"lista.txt");
          directorio_prueba.mkdir();
          fichero.createNewFile();

          out = new FileWriter(".\\alumnos\\lista.txt");
          for(int i=1; i<=20;i++) {
        	  System.out.println("Escribe el nombre del alumno " +i);
        	  String nombre =sc.nextLine();
        	  System.out.println("Escribe el apellido del alumno " + i);
        	  String apellido=sc.nextLine();
        	  out.write("Nombre " + nombre + "Apellido " + apellido+ "\n");
          }
          
     } finally {
       if (out != null)
       out.close();
     }
  }
}

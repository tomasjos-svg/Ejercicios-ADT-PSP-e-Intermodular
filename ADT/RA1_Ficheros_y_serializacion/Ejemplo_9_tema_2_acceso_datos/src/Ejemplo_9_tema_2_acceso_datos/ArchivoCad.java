package Ejemplo_9_tema_2_acceso_datos;

import java.io.*;
import java.util.Scanner;
class ArchivoCad {
   public static void main(String[] args)    {
      String cad = "entrada de datos";
      //Scanner sc=new Scanner(System.in);
      System.out.println("Escritura de datos en fichero");
      byte [] s=new byte[cad.length()];
      try {
           FileOutputStream f = new FileOutputStream(".//cadena.txt",true);
           s=cad.getBytes();
           f.write(s);
           f.write((byte)'\n');
           /*
           System.out.println("Escribe una linea de texto para introducir en el fichero. Escribe FIN para terminar");
           String entrada= sc.nextLine();
           while (!entrada.equalsIgnoreCase("FIN")) {
        	   s=entrada.getBytes();
        	   f.write(s);
        	   f.write((byte)'\n');
        	   System.out.println("Escribe una linea de texto para introducir en el fichero. Escribe FIN para terminar");
        	   entrada=sc.nextLine();
           }*/
           f.close();
        } 
    catch (IOException e)   {
       System.out.println("Anomalia en flujo de salida");
   }
   } 
}

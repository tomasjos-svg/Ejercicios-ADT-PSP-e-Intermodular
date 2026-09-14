package Ejemplo_9_tema_2_acceso_datos;

import java.io.*;
class ArchivoCad {
   public static void main()    {
      String cad = "entrada de datos";
      System.out.println("Escritura de datos en fichero");
      byte [] s=new byte[cad.length()];
      try {
           FileOutputStream f = new FileOutputStream(".//cadena.txt",true);
           s=cad.getBytes();
           f.write(s);
           f.write((byte)'\n');
           f.close();
        } 
    catch (IOException e)   {
       System.out.println("Anomalia en flujo de salida");
   }
   } 
}

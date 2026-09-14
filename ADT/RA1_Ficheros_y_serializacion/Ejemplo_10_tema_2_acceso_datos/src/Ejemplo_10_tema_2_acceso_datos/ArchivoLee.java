package Ejemplo_10_tema_2_acceso_datos;

import java.io.*;
class ArchivoLee{
	public static void main(String[] args)   {
     int c;
      try {
           FileInputStream f = new FileInputStream(".//cadena.txt");
           while((c=f.read())!=-1)        {
              System.out.print((char)c);
        }
        f.close();
       } 
    catch (IOException e)   {
       System.out.println("Anomalia en flujo de entrada");
   }
   } 
}

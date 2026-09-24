package Ejemplo_7_tema_2_acceso_datos;

import java.io.*;
public class LeeStrings {
 public static void main(String[] args)  throws IOException {
    String str="", num;    double x=0;   BufferedReader ent = null;
   try {
      ent = new BufferedReader(
      new FileReader("c:\\users\\tomas\\downloads\\d3.txt"));
      do {
           str=ent.readLine(); // lee una línea
           if (str!=null) { 
              //num=ent.readLine();
              try {
                 x=Double.parseDouble(str);
                 System.out.println(str + " " + x);
               } catch (NumberFormatException e) {
              System.out.println ("Error al leer la linea, no hay un número: "+str);
             } // try
          } // if
      } while (str!=null);
      } finally {
      ent.close();
      } // try
    } // main
} // close


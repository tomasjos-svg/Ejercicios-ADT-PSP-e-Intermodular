package Ejemplo_7_tema_2_acceso_datos;

import java.io.*;
public class LeeStrings {
 public static void main(String[] args)  throws IOException {
    String str="", num;    double x=0;   BufferedReader ent = null;
   try {
      ent = new BufferedReader(
      new FileReader("c:\\users\\tomas\\downloads\\d2.txt"));
      do {
           str=ent.readLine(); // lee una línea
           if (str!=null) { 
              num=ent.readLine();
              try {
                 x=Double.parseDouble(num);
               } catch (NumberFormatException e) {
              System.out.println ("Error al leer el numero: "+num);
             } // try
          } // if
      } while (str!=null);
      } finally {
      if (ent!=null) { System.out.println(str + " " + x); ent.close();}
      } // try
    } // main
} // close


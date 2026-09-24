package Ejemplo_9_tema_2_acceso_datos;
import java.io.*;
import java.util.Scanner;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
class ArchivoCad {
   public static void main(String[] args)    {
      String cad = "entrada de datos";
      Scanner sc=new Scanner(System.in);
      System.out.println("Escritura de datos en fichero");
      byte [] s=new byte[cad.length()];
      try {
           FileOutputStream f = new FileOutputStream(".//cadena.txt",true);
           s=cad.getBytes();
           f.write(s);
           f.write((byte)'\n');
           System.out.println("Escribe una linea de texto para introducir en el fichero. Escribe FIN para terminar");
           String entrada= sc.nextLine();
           s=new byte[entrada.length()];
           while (!entrada.equalsIgnoreCase("FIN")) {
        	   s=entrada.getBytes();
        	   f.write(s);
        	   f.write((byte)'\n');
        	   System.out.println("Escribe una linea de texto para introducir en el fichero. Escribe FIN para terminar");
        	   entrada=sc.nextLine();
           }
           f.close();
           FileInputStream g= new FileInputStream(".//cadena.txt");
           int c;
           while((c=g.read())!=-1){
        	   if((char)c=='\n') {
        		   System.out.println();
        	   }
        	   else {
        		   System.out.print((char)c);
        	   }
           }
           g.close();
           
           FileOutputStream h=new FileOutputStream(".//cadena_bis.dat",true);
           DataOutputStream entradadatos=new DataOutputStream(h);
           System.out.println("Escribe una linea de texto para introducir en el fichero. Escribe FIN para terminar");
           entrada= sc.nextLine();
           
           while (!entrada.equalsIgnoreCase("FIN")) {
        	   entradadatos.writeUTF(entrada);
        	   System.out.println("Escribe una linea de texto para introducir en el fichero. Escribe FIN para terminar");
               entrada= sc.nextLine();               
           }
           h.close();
           System.out.println("lectura del fichero");
           FileInputStream i=new FileInputStream(".//cadena_bis.dat");
           DataInputStream salidadatos=new DataInputStream(i);
           String salida;
           while (true) {
        	   salida=salidadatos.readUTF();
        	   System.out.println(salida);                     
           }
           
        } 
      catch (EOFException e) {
    	  System.out.println("Fin de fichero");
      }
    catch (IOException e)   {
       System.out.println("Anomalia en flujo de salida");
   }
   } 
}

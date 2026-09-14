package Ejemplo_URL_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
public class Principal{
 public static void main(String[] args)  {
 try {
     URL objeto_URL = new URL("http://www.educantabria.es");
      System.out.println("Protocolo:");
      System.out.println(objeto_URL.getProtocol());
      System.out.print("Equipo : ");
      System.out.println(objeto_URL.getHost());
      System.out.print("Archivo:");
      System.out.println(objeto_URL.getFile()); 
      System.out.println("Puerto:");
      System.out.println(objeto_URL.getPort());
      System.out.print("Referencia: ");
      System.out.println(objeto_URL.getRef());
      BufferedReader canal = new BufferedReader( new InputStreamReader(objeto_URL.openStream()));
      String linea;
      while ((linea = canal.readLine()) != null)
        System.out.println(linea);
      canal.close();
 }
 catch(MalformedURLException e) {
      System.out.print("Fallo de URL: ");
      System.out.println(e.getMessage());      
 }
 catch(IOException e) {
     System.out.print("Fallo de I/O: ");
     System.out.println(e.getMessage());
 }
 }
}

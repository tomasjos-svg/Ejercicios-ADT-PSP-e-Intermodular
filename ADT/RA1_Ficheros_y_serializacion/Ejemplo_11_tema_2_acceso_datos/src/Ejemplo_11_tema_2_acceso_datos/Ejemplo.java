package Ejemplo_11_tema_2_acceso_datos;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

//Suponemos creada la clase Coche, con tres atributos
public class Ejemplo {
  public static void main(String[] args) {
      String fichero = ".\\maniobra\\datos.txt";
      FileOutputStream escritor;
      ObjectOutputStream escritorO;
      FileInputStream lector = null;
      ObjectInputStream lectorO = null;
      System.out.println("------ Guardar objetos sobre el fichero .\\maniobra\\datos.txt");
      try {
          escritor = new FileOutputStream(fichero);
          escritorO = new ObjectOutputStream(escritor);
          Coche c1 = new Coche("BI-0402-BB", "Ford Escort", 1800);
          escritorO.writeObject(c1);
          Coche c2 = new Coche("S-9295-AC", "Seat Ibiza", 1600);
          escritorO.writeObject(c2);
          escritorO.close();
          escritor.close();
      } catch (IOException ex) {
          System.out.println("Error: " + ex.getMessage());
      }        
      System.out.println("------ Leer objetos del fichero .\\maniobra\\datos.txt");
      try {
          lector = new FileInputStream(fichero);
          lectorO = new ObjectInputStream(lector);
          Coche c;
          c = (Coche) lectorO.readObject();
          while (true) {
              System.out.println("Matrícula: " + c.getMatricula() + " Marca: " + c.getMarca() + " Cilindrada: " + c.getCilindrada());
              c = (Coche) lectorO.readObject();
          }
      } catch (EOFException ex) {
      } catch (ClassNotFoundException | IOException ex) {
          System.out.println("Error: " + ex.getMessage());
      }        
      try {
          lectorO.close();
          lector.close();
      } catch (IOException ex) {
          System.out.println("Error: " + ex.getMessage());
      }
  }    
}

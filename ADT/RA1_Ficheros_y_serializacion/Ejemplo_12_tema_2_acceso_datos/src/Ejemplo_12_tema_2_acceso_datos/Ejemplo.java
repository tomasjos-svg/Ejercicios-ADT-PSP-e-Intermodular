package Ejemplo_12_tema_2_acceso_datos;

import java.io.*;
public class Ejemplo {
    public static void main(String[] args) {
        File fichero = new File(".\\maniobra\\datos.txt");
        RandomAccessFile manejador;
        System.out.println("Escribir en .\\maniobra\\datos.txt");
        try {            
            manejador = new RandomAccessFile(fichero,"rw");
            StringBuilder codigo, nombre;
            System.out.println("-- Escribiendo registro 1");
            manejador.seek(100);
            codigo = new StringBuilder("S");
            codigo.setLength(2);
            manejador.writeChars(codigo.toString());
            nombre = new StringBuilder("Cantabria");
            nombre.setLength(15);
            System.out.println(nombre.toString());
            manejador.writeChars(nombre.toString());
            manejador.writeInt(50345);
            manejador.close();            
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        try {
            manejador = new RandomAccessFile(fichero,"r");
            char ac[];
            int i;            
            manejador.seek(100);
            ac = new char[2];
            ac[0] = manejador.readChar();
            System.out.println(ac[0]);
            ac[1] = manejador.readChar();
            System.out.println(ac[1]);
        } catch (EOFException ex) {
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }        
    }    
}

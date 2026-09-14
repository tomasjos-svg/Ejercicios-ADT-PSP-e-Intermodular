package Ejercicio_17_tema_2_acceso_datos;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Escribe una frase:");
        String frase = sc.nextLine();

        // =====================================
        // ESCRIBIR LA FRASE EN BINARIO
        // =====================================

        try {

            FileOutputStream fichero =
                    new FileOutputStream("frase.dat");

            DataOutputStream salida =
                    new DataOutputStream(fichero);

            salida.writeUTF(frase);

            salida.close();
            fichero.close();

            System.out.println(
                    "Frase guardada correctamente."
            );

        } catch (IOException e) {

            System.out.println(
                    "Error al escribir: " + e.getMessage()
            );
        }


        // =====================================
        // LEER LA FRASE DEL FICHERO BINARIO
        // =====================================

        try {

            FileInputStream fichero =
                    new FileInputStream("frase.dat");

            DataInputStream entrada =
                    new DataInputStream(fichero);

            String fraseRecuperada =
                    entrada.readUTF();

            entrada.close();
            fichero.close();

            System.out.println();
            System.out.println("Frase recuperada:");
            System.out.println(fraseRecuperada);

        } catch (IOException e) {

            System.out.println(
                    "Error al leer: " + e.getMessage()
            );
        }

        sc.close();
    }
}
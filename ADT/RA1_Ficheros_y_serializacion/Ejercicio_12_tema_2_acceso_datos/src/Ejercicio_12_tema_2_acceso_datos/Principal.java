package Ejercicio_12_tema_2_acceso_datos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);

        // ArrayList inicialmente vacío
        ArrayList<Contacto> contactos =
                new ArrayList<Contacto>();


        // ============================================
        // CREAR EL FICHERO SI NO EXISTE
        // ============================================

        File fichero = new File("contactos.txt");

        if (!fichero.exists()) {
            fichero.createNewFile();
        }


        // ============================================
        // CARGAR EL FICHERO EN EL ARRAYLIST
        // ============================================

        BufferedReader entrada =
                new BufferedReader(
                        new FileReader(fichero)
                );

        String cadena = entrada.readLine();

        while (cadena != null) {

            // Separar los datos de la línea
            String[] datos = cadena.split(" ");

            // Crear un objeto Contacto
            Contacto contacto =
                    new Contacto(
                            datos[0],
                            datos[1],
                            datos[2]
                    );

            // Añadirlo al ArrayList
            contactos.add(contacto);

            // Leer siguiente línea
            cadena = entrada.readLine();
        }

        entrada.close();


        // ============================================
        // MENÚ
        // ============================================

        int opcion = 0;

        while (opcion != 3) {

            System.out.println();
            System.out.println("===== AGENDA =====");
            System.out.println("1. Añadir contacto");
            System.out.println("2. Visualizar contactos");
            System.out.println("3. Salir");
            System.out.print("Elige una opción: ");

            opcion = Integer.parseInt(sc.nextLine());


            switch (opcion) {


            // ========================================
            // OPCIÓN 1 - AÑADIR CONTACTO
            // ========================================

            case 1:

                System.out.println();
                System.out.println("--- NUEVO CONTACTO ---");

                System.out.print("DNI: ");
                String dni = sc.nextLine();

                System.out.print("Nombre: ");
                String nombre = sc.nextLine();

                System.out.print("Teléfono: ");
                String telefono = sc.nextLine();


                // ------------------------------------
                // COMPROBAR SI EL DNI EXISTE
                // ------------------------------------

                boolean encontrado = false;

                BufferedReader lector =
                        new BufferedReader(
                                new FileReader(fichero)
                        );

                cadena = lector.readLine();


                while (cadena != null && encontrado == false) {

                    String[] datos =
                            cadena.split(" ");

                    if (datos[0].equalsIgnoreCase(dni)) {

                        encontrado = true;

                    } else {

                        cadena = lector.readLine();
                    }
                }

                lector.close();


                // ------------------------------------
                // SI NO EXISTE, LO AÑADIMOS
                // ------------------------------------

                if (encontrado == false) {

                    // Crear objeto
                    Contacto nuevoContacto =
                            new Contacto(
                                    dni,
                                    nombre,
                                    telefono
                            );


                    // Añadir al ArrayList
                    contactos.add(nuevoContacto);


                    // Añadir al fichero
                    FileWriter escritor =
                            new FileWriter(
                                    fichero,
                                    true
                            );

                    escritor.append(
                            dni + " "
                            + nombre + " "
                            + telefono
                            + System.lineSeparator()
                    );

                    escritor.close();


                    System.out.println(
                            "Contacto añadido correctamente."
                    );

                } else {

                    System.out.println(
                            "Ya existe un contacto con ese DNI."
                    );
                }

                break;


            // ========================================
            // OPCIÓN 2 - VISUALIZAR CONTACTOS
            // ========================================

            case 2:

                System.out.println();
                System.out.println("--- LISTA DE CONTACTOS ---");

                if (contactos.isEmpty()) {

                    System.out.println(
                            "No hay contactos."
                    );

                } else {

                    for (Contacto contacto : contactos) {

                        System.out.println(contacto);
                    }
                }

                break;


            // ========================================
            // OPCIÓN 3 - SALIR
            // ========================================

            case 3:

                System.out.println();
                System.out.println(
                        "Hasta pronto."
                );

                break;


            // ========================================
            // OPCIÓN INCORRECTA
            // ========================================

            default:

                System.out.println(
                        "Opción incorrecta."
                );
            }
        }

        sc.close();
    }
}
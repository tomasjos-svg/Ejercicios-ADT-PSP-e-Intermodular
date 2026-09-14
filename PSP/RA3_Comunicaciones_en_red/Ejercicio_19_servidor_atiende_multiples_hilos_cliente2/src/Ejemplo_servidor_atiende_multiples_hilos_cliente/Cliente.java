package Ejemplo_servidor_atiende_multiples_hilos_cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            Socket cliente = new Socket(InetAddress.getLocalHost(), 1250);

            ObjectOutputStream salida =
                    new ObjectOutputStream(cliente.getOutputStream());
            salida.flush();

            ObjectInputStream entrada =
                    new ObjectInputStream(cliente.getInputStream());

            System.out.println("Introduce el usuario:");
            String usuario = sc.nextLine();
            salida.writeObject(usuario);
            salida.flush();

            System.out.println("Introduce la contraseña:");
            String contraseña = sc.nextLine();
            salida.writeObject(contraseña);
            salida.flush();

            String respuesta = (String) entrada.readObject();

            if (respuesta.equals("0")) {
                System.out.println("Usuario o contraseña incorrectos.");

            } else if (respuesta.equals("1")) {
                System.out.println("Bienvenido, usuario " + usuario);

                String fechaHora = (String) entrada.readObject();
                System.out.println(fechaHora);

            } else if (respuesta.equals("2")) {
                System.out.println("Bienvenido, administrador " + usuario);

                String opcion = "";

                while (!opcion.equals("3")) {
                    System.out.println("Menú de opciones:");
                    System.out.println("1- Crear usuario");
                    System.out.println("2- Borrar usuario");
                    System.out.println("3- Salir");

                    opcion = sc.nextLine();

                    salida.writeObject(opcion);
                    salida.flush();

                    switch (opcion) {
                        case "1":
                            crearUsuario(sc, salida);
                            break;

                        case "2":
                            borrarUsuario(sc, salida);
                            break;

                        case "3":
                            break;

                        default:
                            break;
                    }

                    String confirmacion = (String) entrada.readObject();
                    System.out.println(confirmacion);

                    String listado = (String) entrada.readObject();
                    System.out.println(listado);
                }
            }

            entrada.close();
            salida.close();
            cliente.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void crearUsuario(Scanner sc, ObjectOutputStream salida)
            throws IOException {

        System.out.println("Introduce el nombre del usuario:");
        String nombre = sc.nextLine();

        System.out.println("Introduce la contraseña:");
        String contraseña = sc.nextLine();

        System.out.println("Introduce el nivel de permisos:");
        String permisos = sc.nextLine();

        Usuario usuario = new Usuario(nombre, contraseña, permisos);

        salida.writeObject(usuario);
        salida.flush();
    }

    public static void borrarUsuario(Scanner sc, ObjectOutputStream salida)
            throws IOException {

        System.out.println("Introduce el nombre del usuario que quieres borrar:");
        String nombre = sc.nextLine();

        salida.writeObject(nombre);
        salida.flush();
    }
}
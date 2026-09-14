package Ejemplo_servidor_atiende_multiples_hilos_cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



public class Hilo extends Thread {

    private Socket cliente;
    private ListaUsuarios lista;

    public Hilo(Socket cliente, ListaUsuarios lista) {
        this.cliente = cliente;
        this.lista = lista;
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream salida =
                    new ObjectOutputStream(cliente.getOutputStream());
            salida.flush();

            ObjectInputStream entrada =
                    new ObjectInputStream(cliente.getInputStream());

            String nombre = (String) entrada.readObject();
            String contraseña = (String) entrada.readObject();

            Usuario usuario = lista.autenticar(nombre, contraseña);

            if (usuario == null) {
                salida.writeObject("0");
                salida.flush();
            } else if (usuario.getPermisos().equalsIgnoreCase("USUARIO")) {
                salida.writeObject("1");
                salida.flush();

                LocalDateTime fechaHora = LocalDateTime.now();
                DateTimeFormatter formato =
                        DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

                salida.writeObject("La fecha y la hora son: "
                        + fechaHora.format(formato));
                salida.flush();

            } else if (usuario.getPermisos().equalsIgnoreCase("ADMINISTRADOR")) {
                salida.writeObject("2");
                salida.flush();

                String opcion = "";

                while (!opcion.equals("3")) {
                    opcion = (String) entrada.readObject();

                    switch (opcion) {
                        case "1":
                            Usuario nuevoUsuario =
                                    (Usuario) entrada.readObject();

                            lista.añadir(nuevoUsuario);

                            salida.writeObject("Usuario creado correctamente.");
                            salida.writeObject(lista.mostrarUsuarios());

                            salida.flush();
                            break;

                        case "2":
                            String nombreBorrar =
                                    (String) entrada.readObject();

                            boolean borrado = lista.borrar(nombreBorrar);
                            lista.mostrarUsuarios();

                            if (borrado) {
                                salida.writeObject("Usuario borrado correctamente.");
                            } else {
                                salida.writeObject("No existe ningún usuario con ese nombre.");
                            }

                            salida.flush();
                            break;

                        case "3":
                            salida.writeObject("Fin de sesión.");
                            salida.flush();
                            break;

                        default:
                            salida.writeObject("Opción no válida.");
                            salida.flush();
                            break;
                    }
                }
            }

            entrada.close();
            salida.close();
            cliente.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
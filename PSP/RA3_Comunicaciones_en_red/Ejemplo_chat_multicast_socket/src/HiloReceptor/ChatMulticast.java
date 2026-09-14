package HiloReceptor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.InterfaceAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.util.Collections;

public class ChatMulticast {

    public static void main(String[] args) {

        try {
            String nombre;

            InetAddress grupo = InetAddress.getByName("239.1.1.1");
            int puerto = 4446;

            MulticastSocket socket = new MulticastSocket(puerto);

            NetworkInterface interfaz = buscarInterfazIPv4Multicast();

            if (interfaz == null) {
                throw new IOException(
                        "No se encontró ninguna interfaz IPv4 con soporte multicast.");
            }

            socket.setNetworkInterface(interfaz);

            InetSocketAddress direccionGrupo =
                    new InetSocketAddress(grupo, puerto);

            socket.joinGroup(direccionGrupo, interfaz);

            HiloReceptor receptor = new HiloReceptor(socket);
            receptor.start();

            BufferedReader teclado =
                    new BufferedReader(
                            new InputStreamReader(System.in));

            System.out.print("Introduce tu nombre: ");
            nombre = teclado.readLine();

            System.out.println("Chat iniciado. Escribe \"salir\" para terminar.");

            String linea;

            while (!(linea = teclado.readLine()).equalsIgnoreCase("salir")) {

                String mensaje = nombre + ": " + linea;
                byte[] datos = mensaje.getBytes();

                DatagramPacket paquete =
                        new DatagramPacket(
                                datos,
                                datos.length,
                                grupo,
                                puerto);

                socket.send(paquete);
            }

            socket.leaveGroup(direccionGrupo, interfaz);
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static NetworkInterface buscarInterfazIPv4Multicast()
            throws IOException {

        for (NetworkInterface ni :
                Collections.list(NetworkInterface.getNetworkInterfaces())) {

            if (!ni.isUp() || ni.isLoopback() || !ni.supportsMulticast()) {
                continue;
            }

            for (InterfaceAddress ia : ni.getInterfaceAddresses()) {
                if (ia.getAddress() instanceof Inet4Address) {
                    return ni;
                }
            }
        }

        return null;
    }
}
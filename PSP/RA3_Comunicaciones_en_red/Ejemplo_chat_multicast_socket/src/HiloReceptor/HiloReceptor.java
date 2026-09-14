package HiloReceptor;

import java.net.*;

public class HiloReceptor extends Thread {

    private MulticastSocket socket;
    public HiloReceptor(MulticastSocket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            byte[] buffer = new byte[1024];
            while (true) {
                DatagramPacket paquete =
                        new DatagramPacket(buffer, buffer.length);
                socket.receive(paquete);
                String mensaje =
                        new String(
                                paquete.getData(),
                                0,
                                paquete.getLength());
                System.out.println(mensaje);
            }
        } catch (Exception e) {
            System.out.println("Receptor finalizado.");
        }
    }
}

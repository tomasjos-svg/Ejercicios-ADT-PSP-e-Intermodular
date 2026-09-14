package Ejemplo_compartir_objeto_entre_hilos;

import java.util.ArrayList;

public class ListaMensajes {
    private ArrayList<String> mensajes;
    public ListaMensajes() {
        mensajes = new ArrayList<>();
    }
    public synchronized void añadirMensaje(String mensaje) {
        mensajes.add(mensaje);
    }
    public synchronized void mostrarMensajes() {
        for (String m : mensajes) {
            System.out.println(m);
        }
    }
}

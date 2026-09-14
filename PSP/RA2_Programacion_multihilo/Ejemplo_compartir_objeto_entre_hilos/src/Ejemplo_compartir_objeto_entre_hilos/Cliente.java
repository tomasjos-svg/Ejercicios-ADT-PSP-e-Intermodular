package Ejemplo_compartir_objeto_entre_hilos;

public class Cliente extends Thread {

    private ListaMensajes lista;
    private String nombre;

    public Cliente(ListaMensajes lista, String nombre) {
        this.lista = lista;
        this.nombre = nombre;
    }

    @Override
    public void run() {

        for (int i = 1; i <= 5; i++) {
            lista.añadirMensaje(
                    nombre + " escribe mensaje " + i);
        }
    }
}

package Ejemplo_compartir_objeto_entre_hilos;

public class Principal {

    public static void main(String[] args)
            throws InterruptedException {
        ListaMensajes lista = new ListaMensajes();
        Cliente h1 =
                new Cliente(lista, "Cliente 1");
        Cliente h2 =
                new Cliente(lista, "Cliente 2");
        h1.start();
        h2.start();

        h1.join();
        h2.join();
        lista.mostrarMensajes();
    }
}

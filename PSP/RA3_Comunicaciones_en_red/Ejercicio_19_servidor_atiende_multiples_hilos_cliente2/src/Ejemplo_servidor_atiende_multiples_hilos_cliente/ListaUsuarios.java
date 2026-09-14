package Ejemplo_servidor_atiende_multiples_hilos_cliente;

import java.util.ArrayList;
import java.util.Iterator;

public class ListaUsuarios {

    private ArrayList<Usuario> lista;

    public ListaUsuarios() {
        lista = new ArrayList<>();
    }

    public synchronized void añadir(Usuario u) {
        lista.add(u);
    }

    public synchronized boolean borrar(String nombre) {
        Iterator<Usuario> it = lista.iterator();

        while (it.hasNext()) {
            Usuario u = it.next();

            if (u.getNombre().equalsIgnoreCase(nombre)) {
                it.remove();
                return true;
            }
        }

        return false;
    }

    public synchronized Usuario autenticar(String nombre, String contraseña) {
        for (Usuario u : lista) {
            if (u.getNombre().equalsIgnoreCase(nombre)
                    && u.getContraseña().equals(contraseña)) {
                return u;
            }
        }

        return null;
    }

    public synchronized String mostrarUsuarios() {

        StringBuilder sb = new StringBuilder();

        sb.append("=== LISTA DE USUARIOS ===\n");

        Iterator<Usuario> it = lista.iterator();

        while (it.hasNext()) {

            Usuario usuario = it.next();

            sb.append("Usuario: ")
              .append(usuario.getNombre())
              .append(" | Contraseña: ")
              .append(usuario.getContraseña())
              .append(" | Permisos: ")
              .append(usuario.getPermisos())
              .append("\n");
        }

        sb.append("=========================");

        return sb.toString();
    }
}
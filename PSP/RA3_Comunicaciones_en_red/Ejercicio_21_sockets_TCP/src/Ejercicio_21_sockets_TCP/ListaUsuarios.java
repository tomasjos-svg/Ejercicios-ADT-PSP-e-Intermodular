package Ejercicio_21_sockets_TCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        	System.out.println("usuario de la lista que estamos comprobando");
        	System.out.println(u.getNombre()+ " "+ u.getContraseña());
        	System.out.println(" usuario introducido por teclado");
        	System.out.println(nombre + " " + contraseña);
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
              .append(" | DNI: ")
              .append(usuario.getContraseña())
              .append("\n");
        }

        sb.append("=========================");

        return sb.toString();
    }
    public synchronized void cargarDesdeBD() {

        String sql = "SELECT dni, nombre, contrasena FROM alumno";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            lista.clear();

            while (rs.next()) {
                String dni = rs.getString("dni");
                String nombre = rs.getString("nombre");
                String contrasena = rs.getString("contrasena");

                Usuario alumno = new Usuario(nombre, contrasena, dni);

                lista.add(alumno);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
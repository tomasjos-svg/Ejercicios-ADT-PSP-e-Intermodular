package Ejercicio_21_sockets_TCP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ListaCalificaciones {

    private ArrayList<Calificaciones> lista;

    public ListaCalificaciones() {
        lista = new ArrayList<>();
    }

    public ArrayList<Calificaciones> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Calificaciones> lista) {
        this.lista = lista;
    }

    public void añadir(Calificaciones c) {
        lista.add(c);
    }

    public Calificaciones buscar(String dni) {

        for (Calificaciones c : lista) {
            if (c.getDni().equalsIgnoreCase(dni)) {
                return c;
            }
        }

        return null;
    }

    public void mostrar() {

        for (Calificaciones c : lista) {
            System.out.println(c);
        }
    }
    public synchronized void cargarDesdeBD() {

        String sql = "SELECT dni, psp, ad, pmdm, di FROM calificaciones";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            lista.clear();

            while (rs.next()) {
                String dni = rs.getString("dni");
                double psp = rs.getDouble("psp");
                double ad = rs.getDouble("ad");
                double pmdm = rs.getDouble("pmdm");
                double di = rs.getDouble("di");

                Calificaciones c =
                        new Calificaciones(dni, psp, ad, pmdm, di);

                lista.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
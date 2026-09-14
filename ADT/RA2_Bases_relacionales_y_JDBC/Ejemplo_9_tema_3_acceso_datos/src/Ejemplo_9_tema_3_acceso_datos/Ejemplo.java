package Ejemplo_9_tema_3_acceso_datos;

import java.sql.*;
public class Ejemplo {
    private static Connection conexion;
    private static String bd = "alumno";
    private static String user = "alumno";
    private static String password = System.getenv().getOrDefault("DB_PASSWORD", "password");
    private static String host = "localhost";
    private static String server = "jdbc:mysql://" + host + ":3307/" + bd;
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(server, user, password);
            conexion.setAutoCommit(false);
        } catch (ClassNotFoundException ex) {
            System.out.println("Error con el driver: " + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Error de conexion: " + ex.getMessage());
        }
        try {
            String sql = "DELETE FROM profesor WHERE DNI = ?";
            PreparedStatement s = conexion.prepareStatement(sql);
            s.setString(1, "2345");
            int resultado = s.executeUpdate();
            if (resultado == 1) {
                conexion.commit();
                System.out.println("Profesor eliminado correctamente");
            } else {
                conexion.rollback();
                System.out.println("No se encontro ningun profesor con ese DNI");
            }
            s.close();
            conexion.close();
        } catch (SQLException ex) {
            System.out.println("Error SQL: " + ex.getMessage());
        }
    }
}


package Ejercicio_21_sockets_TCP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    public static Connection conectar() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/ejercicio21";
        String usuario = "root";
        String password = System.getenv().getOrDefault("DB_PASSWORD", "password");

        return DriverManager.getConnection(url, usuario, password);
    }
}
package com.practica.Practica_VII_Tema_3_ADT_gestion_alumnos.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionOracle {
    private static final String URL =
        "jdbc:oracle:thin:@//localhost:1521/FREEPDB1";
    private static final String USUARIO = "alumno";
    private static final String CLAVE = "Alumno123!";

    private ConexionOracle() { }

    public static Connection abrir() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CLAVE);
    }
}

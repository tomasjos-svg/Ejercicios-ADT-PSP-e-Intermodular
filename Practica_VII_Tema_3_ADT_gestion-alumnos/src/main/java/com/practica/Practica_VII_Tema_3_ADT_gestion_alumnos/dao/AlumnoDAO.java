package com.practica.Practica_VII_Tema_3_ADT_gestion_alumnos.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.practica.Practica_VII_Tema_3_ADT_gestion_alumnos.model.Alumno;
import com.practica.Practica_VII_Tema_3_ADT_gestion_alumnos.model.Direccion;
public class AlumnoDAO {
    public ArrayList<Alumno> cargarTodos() throws SQLException {
        ArrayList<Alumno> alumnos = new ArrayList<>();
        String sql = "SELECT CLAVE, NOMBRE, APELLIDOS, EDAD, " +
                     "CALLE, NUMERO, CODIGO FROM ALUMNOS " +
                     "ORDER BY CLAVE";
        try (Connection con = ConexionOracle.abrir();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Direccion d = new Direccion(
                    rs.getString("CALLE"),
                    rs.getInt("NUMERO"),
                    rs.getString("CODIGO"));
                Alumno a = new Alumno(
                    rs.getString("CLAVE"),
                    rs.getString("NOMBRE"),
                    rs.getString("APELLIDOS"),
                    rs.getInt("EDAD"), d);
                alumnos.add(a);
            }
        }
        return alumnos;
    }
    public Alumno buscarPorClave(String clave) throws SQLException {
        String sql = "SELECT CLAVE,NOMBRE,APELLIDOS,EDAD," +
                     "CALLE,NUMERO,CODIGO FROM ALUMNOS WHERE CLAVE=?";
        try (Connection con = ConexionOracle.abrir();
             PreparedStatement ps = con.prepareStatement(sql)) {
          ps.setString(1, clave);
          try (ResultSet rs = ps.executeQuery()) {
            if (!rs.next()) return null;
            Direccion d = new Direccion(rs.getString("CALLE"),
                rs.getInt("NUMERO"), rs.getString("CODIGO"));
            return new Alumno(rs.getString("CLAVE"), rs.getString("NOMBRE"),
                rs.getString("APELLIDOS"), rs.getInt("EDAD"), d);
          }
        }
      }

      private Alumno leerAlumno(ResultSet rs) throws SQLException {
        Direccion d = new Direccion(rs.getString("CALLE"),
            rs.getInt("NUMERO"), rs.getString("CODIGO"));
        return new Alumno(rs.getString("CLAVE"), rs.getString("NOMBRE"),
            rs.getString("APELLIDOS"), rs.getInt("EDAD"), d);
      }

    public void guardarTodos(List<Alumno> alumnos)
            throws SQLException {
        String borrar = "DELETE FROM ALUMNOS";
        String insertar =
            "INSERT INTO ALUMNOS " +
            "(CLAVE,NOMBRE,APELLIDOS,EDAD,CALLE,NUMERO,CODIGO) " +
            "VALUES (?,?,?,?,?,?,?)";
        try (Connection con = ConexionOracle.abrir()) {
            con.setAutoCommit(false);
            try (Statement st = con.createStatement();
                 PreparedStatement ps = con.prepareStatement(insertar)) {
                st.executeUpdate(borrar);
                for (Alumno a : alumnos) {
                    ps.setString(1, a.getClave());
                    ps.setString(2, a.getNombre());
                    ps.setString(3, a.getApellidos());
                    ps.setInt(4, a.getEdad());
                    ps.setString(5, a.getDireccion().getCalle());
                    ps.setInt(6, a.getDireccion().getNumero());
                    ps.setString(7, a.getDireccion().getCodigoPostal());
                    ps.addBatch();
                }

                ps.executeBatch();
                con.commit();

            } catch (SQLException e) {
                con.rollback();
                throw e;
            } finally {
                con.setAutoCommit(true);
            }
        }
    }
    public int insertar(Alumno a) throws SQLException {
    	  String sql = "INSERT INTO ALUMNOS " +
    	    "(CLAVE,NOMBRE,APELLIDOS,EDAD,CALLE,NUMERO,CODIGO) " +
    	    "VALUES (?,?,?,?,?,?,?)";
    	  try (Connection con = ConexionOracle.abrir();
    	       PreparedStatement ps = con.prepareStatement(sql)) {
    	    cargarParametros(ps, a);
    	    return ps.executeUpdate();
    	  }
    	}
    public int actualizar(Alumno a) throws SQLException {
    	  String sql = "UPDATE ALUMNOS SET NOMBRE=?,APELLIDOS=?," +
    	    "EDAD=?,CALLE=?,NUMERO=?,CODIGO=? WHERE CLAVE=?";
    	  try (Connection con = ConexionOracle.abrir();
    	       PreparedStatement ps = con.prepareStatement(sql)) {
    	    ps.setString(1, a.getNombre());
    	    ps.setString(2, a.getApellidos());
    	    ps.setInt(3, a.getEdad());
    	    ps.setString(4, a.getDireccion().getCalle());
    	    ps.setInt(5, a.getDireccion().getNumero());
    	    ps.setString(6, a.getDireccion().getCodigoPostal());
    	    ps.setString(7, a.getClave());
    	    return ps.executeUpdate();
    	  }
    	}
    public int eliminar(String clave) throws SQLException {
    	  String sql = "DELETE FROM ALUMNOS WHERE CLAVE=?";
    	  try (Connection con = ConexionOracle.abrir();
    	       PreparedStatement ps = con.prepareStatement(sql)) {
    	    ps.setString(1, clave);
    	    return ps.executeUpdate();
    	  }
    	}
    private void cargarParametros(PreparedStatement ps,
    	    Alumno a) throws SQLException {
    	  ps.setString(1, a.getClave());
    	  ps.setString(2, a.getNombre());
    	  ps.setString(3, a.getApellidos());
    	  ps.setInt(4, a.getEdad());
    	  ps.setString(5, a.getDireccion().getCalle());
    	  ps.setInt(6, a.getDireccion().getNumero());
    	  ps.setString(7, a.getDireccion().getCodigoPostal());
    	}


}


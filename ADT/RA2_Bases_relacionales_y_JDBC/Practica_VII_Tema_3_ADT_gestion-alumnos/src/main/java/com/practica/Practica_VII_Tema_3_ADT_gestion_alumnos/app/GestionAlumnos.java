package com.practica.Practica_VII_Tema_3_ADT_gestion_alumnos.app;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.practica.Practica_VII_Tema_3_ADT_gestion_alumnos.dao.AlumnoDAO;
import com.practica.Practica_VII_Tema_3_ADT_gestion_alumnos.model.Alumno;
import com.practica.Practica_VII_Tema_3_ADT_gestion_alumnos.model.Direccion;

public class GestionAlumnos {
    private static final Scanner TECLADO = new Scanner(System.in);
  //  private static final AlumnoDAO DAO = new AlumnoDAO();
  //  private static ArrayList<Alumno> alumnos;
    private static ArrayList<Alumno> alumnos = new ArrayList<>();
    private static final AlumnoDAO DAO = new AlumnoDAO();
    public static void main(String[] args) {
        try {
            alumnos = DAO.cargarTodos();
            System.out.println("Alumnos cargados desde Oracle: "
                               + alumnos.size());
            menu();
        } catch (SQLException e) {
            System.out.println("Error de base de datos: "
                               + e.getMessage());
        } finally {
            TECLADO.close();
        }
    }
    private static void menu() {
        int opcion;
        do {
            System.out.println("\n--- GESTIÓN DE ALUMNOS ---");
            System.out.println("1. Alta");
            System.out.println("2. Baja");
            System.out.println("3. Modificación");
            System.out.println("4. Listar alumnos");
            System.out.println("0. Salir");
            opcion = leerEntero("Opción: ");
            switch (opcion) {
                case 1: alta(); break;
                case 2: baja(); break;
                case 3: modificar(); break;
                case 4: listar();break;
                case 0: System.out.println("Fin del programa."); break;
                default: System.out.println("Opción no válida."); break;
            }
        } while (opcion != 0);
    }
    private static Alumno buscar(String clave) {
        for (Alumno a : alumnos) {
            if (a.getClave().equalsIgnoreCase(clave)) {
                return a;
            }
        }
        return null;
    }
    /*
    private static void alta() {
        System.out.println("\n--- ALTA ---");
        String clave = leerTexto("Clave (máx. 4): ", 4).toUpperCase();
        if (buscar(clave) != null) {
            System.out.println("Ya existe un alumno con esa clave.");
            return;
        }
        String nombre = leerTexto("Nombre (máx. 10): ", 10);
        String apellidos = leerTexto("Apellidos (máx. 20): ", 20);
        int edad = leerEntero("Edad: ");
        String calle = leerTexto("Calle (máx. 20): ", 20);
        int numero = leerEntero("Número: ");
        String codigo = leerTexto("Código postal (máx. 5): ", 5);

        Direccion d = new Direccion(calle, numero, codigo);
        alumnos.add(new Alumno(clave, nombre, apellidos, edad, d));
        guardarCambios();
    }
    */
    private static void alta() {
    	  String clave = leerTexto("Clave (máx. 4): ", 4).toUpperCase();
    	  if (buscar(clave) != null) {
    	    System.out.println("Ya existe un alumno con esa clave.");
    	    return;
    	  }

    	  Alumno a = new Alumno(clave,
    	      leerTexto("Nombre: ", 10),
    	      leerTexto("Apellidos: ", 20),
    	      leerEntero("Edad: "),
    	      new Direccion(leerTexto("Calle: ", 20),
    	          leerEntero("Número: "),
    	          leerTexto("Código postal: ", 5)));
    	  try {
    	    if (DAO.insertar(a) == 1) recargarAlumnos();
    	  } catch (SQLException e) {
    	    System.out.println(e.getMessage());
    	  }
    	}
    

    private static void recargarAlumnos() throws SQLException {
      alumnos.clear();
      alumnos.addAll(DAO.cargarTodos());
    }
  /*
    private static void baja() {
        System.out.println("\n--- BAJA ---");
        String clave = leerTexto("Clave: ", 4);
        Alumno a = buscar(clave);

        if (a == null) {
            System.out.println("No existe el alumno.");
            return;
        }

        alumnos.remove(a);
        guardarCambios();
    }
    private static void modificar() {
        System.out.println("\n--- MODIFICACIÓN ---");
        String clave = leerTexto("Clave del alumno: ", 4);
        Alumno a = buscar(clave);

        if (a == null) {
            System.out.println("No existe el alumno.");
            return;
        }

        a.setNombre(leerTexto("Nuevo nombre: ", 10));
        a.setApellidos(leerTexto("Nuevos apellidos: ", 20));
        a.setEdad(leerEntero("Nueva edad: "));

        String calle = leerTexto("Nueva calle: ", 20);
        int numero = leerEntero("Nuevo número: ");
        String codigo = leerTexto("Nuevo código postal: ", 5);
        a.setDireccion(new Direccion(calle, numero, codigo));

        guardarCambios();
    }
    */
    private static void baja() {
    	  String clave = leerTexto("Clave: ", 4);
    	  if (buscar(clave) == null) {
    	    System.out.println("No existe el alumno.");
    	    return;
    	  }
    	  try {
    	    if (DAO.eliminar(clave) == 1) recargarAlumnos();
    	  } catch (SQLException e) {
    	    System.out.println(e.getMessage());
    	  }
    	}
    private static void modificar() {
    	  String clave = leerTexto("Clave del alumno: ", 4);
    	  Alumno alumno = buscar(clave);

    	  if (alumno == null) {
    	    System.out.println("No existe el alumno.");
    	    return;
    	  }

    	  alumno.setNombre(leerTexto("Nuevo nombre: ", 10));
    	  alumno.setApellidos(leerTexto("Nuevos apellidos: ", 20));
    	  alumno.setEdad(leerEntero("Nueva edad: "));

    	  String calle = leerTexto("Nueva calle: ", 20);
    	  int numero = leerEntero("Nuevo número: ");
    	  String codigo = leerTexto("Nuevo código postal: ", 5);
    	  alumno.setDireccion(new Direccion(calle, numero, codigo));

    	  try {
    	    if (DAO.actualizar(alumno) == 1) {
    	      recargarAlumnos();
    	      System.out.println("Alumno modificado correctamente.");
    	    }
    	  } catch (SQLException e) {
    	    System.out.println(e.getMessage());
    	  }
    	}

    private static void listar() {
        System.out.println("\n--- ALUMNOS EN MEMORIA ---");
        if (alumnos.isEmpty()) {
            System.out.println("No hay alumnos.");
        } else {
            alumnos.forEach(System.out::println);
        }
    }
    private static void guardarCambios() {
        try {
            DAO.guardarTodos(alumnos);
            System.out.println("Cambios guardados en Oracle.");
        } catch (SQLException e) {
            System.out.println("No se pudieron guardar los cambios: "
                               + e.getMessage());
            try {
                alumnos = DAO.cargarTodos();
                System.out.println("Se ha restaurado la lista desde Oracle.");
            } catch (SQLException ex) {
                System.out.println("No se pudo recargar la lista: "
                                   + ex.getMessage());
            }
        }
    }
    private static String leerTexto(String mensaje, int max) {
        while (true) {
            System.out.print(mensaje);
            String texto = TECLADO.nextLine().trim();
            if (!texto.isEmpty() && texto.length() <= max) {
                return texto;
            }
            System.out.println("Debe contener entre 1 y " + max
                               + " caracteres.");
        }
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = TECLADO.nextLine().trim();
            try {
                return Integer.parseInt(texto);
            } catch (NumberFormatException e) {
                System.out.println("Introduzca un número entero.");
            }
        }
    }
}


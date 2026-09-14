package com.ejemplo.centro.app;
import java.time.LocalDate;
import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.ejemplo.centro.dao.AdministrativoDAO;
import com.ejemplo.centro.dao.AlumnoDAO;
import com.ejemplo.centro.dao.AsignaturaDAO;
import com.ejemplo.centro.dao.CursoDAO;
import com.ejemplo.centro.dao.DepartamentoDAO;
import com.ejemplo.centro.dao.ExpedienteDAO;
import com.ejemplo.centro.dao.ProfesorDAO;
import com.ejemplo.centro.modelo.Administrativo;
import com.ejemplo.centro.modelo.Alumno;
import com.ejemplo.centro.modelo.Asignatura;
import com.ejemplo.centro.modelo.Curso;
import com.ejemplo.centro.modelo.Departamento;
import com.ejemplo.centro.modelo.Expediente;
import com.ejemplo.centro.modelo.Profesor;
import com.ejemplo.centro.util.HibernateUtil;
import com.ejemplo.centro.util.HibernateUtilQueries;
public class AppHibernate {
    private static final Scanner SC = new Scanner(System.in);
    // DAO
    private static final AlumnoDAO ALUMNO_DAO = new AlumnoDAO();
    private static final ProfesorDAO PROFESOR_DAO = new ProfesorDAO();
    private static final AdministrativoDAO ADMINISTRATIVO_DAO =  new AdministrativoDAO();
    private static final CursoDAO CURSO_DAO = new CursoDAO();
    private static final AsignaturaDAO ASIGNATURA_DAO =   new AsignaturaDAO();
    private static final DepartamentoDAO DEPARTAMENTO_DAO =  new DepartamentoDAO();
    private static final ExpedienteDAO EXPEDIENTE_DAO =  new ExpedienteDAO();
    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenuPrincipal();
            opcion = leerEntero("Opción: ");
            try {
            	switch (opcion) {

                case 1 -> menuAlumnos();
                case 2 -> menuProfesores();
                case 3 -> menuAdministrativos();
                case 4 -> menuCursos();
                case 5 -> menuAsignaturas();
                case 6 -> menuDepartamentos();
                case 7 -> menuExpedientes();
                case 8 -> menuProfesorAsignatura();

                case 9 -> alumnosPorCurso();
                case 10 -> profesoresPorDepartamento();
                case 11 -> profesoresPorAsignatura();
                case 12 -> contarAlumnosPorCurso();
                case 13 -> actualizarEspecialidad();
                case 14 -> eliminarRelacionProfesorAsignatura();

                case 0 -> System.out.println("Fin del programa.");

                default -> System.out.println("Opción no válida.");
            }
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        } while (opcion != 0);
        SC.close();
        HibernateUtil.shutdown();
    }

    // =========================================================
    // MENÚ PRINCIPAL
    // =========================================================

    private static void mostrarMenuPrincipal() {
        System.out.println();
        System.out.println("==========================================");
        System.out.println("           CENTRO - HIBERNATE");
        System.out.println("==========================================");

        System.out.println("1. Gestión de alumnos");
        System.out.println("2. Gestión de profesores");
        System.out.println("3. Gestión de administrativos");
        System.out.println("4. Gestión de cursos");
        System.out.println("5. Gestión de asignaturas");
        System.out.println("6. Gestión de departamentos");
        System.out.println("7. Gestión de expedientes");
        System.out.println("8. Gestión Profesor - Asignatura (N:M)");

        System.out.println();
        System.out.println("---------- CONSULTAS HQL ----------");

        System.out.println("9. Mostrar alumnos de un curso");
        System.out.println("10. Mostrar profesores de un departamento");
        System.out.println("11. Mostrar profesores de una asignatura");
        System.out.println("12. Contar alumnos por curso");
        System.out.println("13. Actualizar especialidad de profesor");
        System.out.println("14. Eliminar relación profesor-asignatura");

        System.out.println("0. Salir");
        System.out.println("==========================================");
    }
    // =========================================================
    // ALUMNOS
    // =========================================================
    private static void menuAlumnos() {
        int opcion;
        do {
            System.out.println();
            System.out.println("---------- GESTIÓN DE ALUMNOS ----------");
            System.out.println("1. Crear alumno");
            System.out.println("2. Buscar alumno");
            System.out.println("3. Actualizar alumno");
            System.out.println("4. Eliminar alumno");
            System.out.println("0. Volver");
            opcion = leerEntero("Opción: ");
            try {
                switch (opcion) {
                    case 1 -> crearAlumno();
                    case 2 -> buscarAlumno();
                    case 3 -> actualizarAlumno();
                    case 4 -> eliminarAlumno();
                    case 0 -> { }
                    default -> System.out.println("Opción no válida.");
                }
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        } while (opcion != 0);
    }
    private static void crearAlumno() {
        System.out.println();
        System.out.println("--- Crear alumno ---");
        System.out.print("DNI: ");
        String dni = SC.nextLine();
        System.out.print("Nombre: ");
        String nombre = SC.nextLine();
        System.out.print("Apellidos: ");
        String apellidos = SC.nextLine();
        System.out.print("Código de alumno: ");
        String codAlumno = SC.nextLine();
        int codCurso = leerEntero("Código del curso: ");
        Curso curso = CURSO_DAO.buscar(codCurso);
        if (curso == null) {
            System.out.println("El curso indicado no existe.");
            return;
        }
        Alumno alumno = new Alumno(dni, nombre, apellidos, codAlumno, curso);
        ALUMNO_DAO.crear(alumno);
        System.out.println("Alumno creado correctamente.");
    }
    private static void buscarAlumno() {
        System.out.print("DNI del alumno: ");
        String dni = SC.nextLine();
        Alumno alumno = ALUMNO_DAO.buscar(dni);
        if (alumno == null) {
            System.out.println("No existe ningún alumno con ese DNI.");
        } else {
            System.out.println(alumno);
        }
    }
    private static void actualizarAlumno() {
        System.out.print("DNI del alumno: ");
        String dni = SC.nextLine();
        Alumno alumno = ALUMNO_DAO.buscar(dni);
        if (alumno == null) {
            System.out.println("El alumno no existe.");
            return;
        }
        System.out.print("Nuevo nombre: ");
        alumno.setNombre(SC.nextLine());
        System.out.print("Nuevos apellidos: ");
        alumno.setApellidos(SC.nextLine());
        System.out.print("Nuevo código de alumno: ");
        alumno.setCodAlumno(SC.nextLine());
        int codCurso = leerEntero("Nuevo código de curso: ");
        Curso curso = CURSO_DAO.buscar(codCurso);
        if (curso == null) {
            System.out.println("El curso no existe.");
            return;
        }
        alumno.setCurso(curso);
        ALUMNO_DAO.actualizar(alumno);
        System.out.println("Alumno actualizado correctamente.");
    }
    private static void eliminarAlumno() {
        System.out.print("DNI del alumno: ");
        String dni = SC.nextLine();
        boolean eliminado = ALUMNO_DAO.eliminar(dni);
        if (eliminado) {
            System.out.println("Alumno eliminado correctamente.");
        } else {
            System.out.println("El alumno no existe.");
        }
    }

    // =========================================================
    // PROFESORES
    // =========================================================
    private static void menuProfesores() {
        int opcion;
        do {
            System.out.println();
            System.out.println("--------- GESTIÓN DE PROFESORES ---------");
            System.out.println("1. Crear profesor");
            System.out.println("2. Buscar profesor");
            System.out.println("3. Actualizar profesor");
            System.out.println("4. Eliminar profesor");
            System.out.println("0. Volver");
            opcion = leerEntero("Opción: ");
            try {
                switch (opcion) {
                    case 1 -> crearProfesor();
                    case 2 -> buscarProfesor();
                    case 3 -> actualizarProfesor();
                    case 4 -> eliminarProfesor();
                    case 0 -> { }
                    default -> System.out.println("Opción no válida.");
                }

            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }

        } while (opcion != 0);
    }
    private static void crearProfesor() {
        System.out.println();
        System.out.println("--- Crear profesor ---");
        System.out.print("DNI: ");
        String dni = SC.nextLine();
        System.out.print("Nombre: ");
        String nombre = SC.nextLine();
        System.out.print("Apellidos: ");
        String apellidos = SC.nextLine();
        System.out.print("NRP: ");
        String nrp = SC.nextLine();
        System.out.print("Especialidad: ");
        String especialidad = SC.nextLine();
        int idDepartamento =
                leerEntero("ID del departamento: ");
        Departamento departamento =
                DEPARTAMENTO_DAO.buscar(idDepartamento);
        if (departamento == null) {
            System.out.println("El departamento no existe.");
            return;
        }
        Profesor profesor = new Profesor(
                dni,
                nombre,
                apellidos,
                nrp,
                especialidad,
                departamento
        );
        PROFESOR_DAO.crear(profesor);
        System.out.println("Profesor creado correctamente.");
    }
    private static void buscarProfesor() {
        System.out.print("DNI del profesor: ");
        String dni = SC.nextLine();
        Profesor profesor = PROFESOR_DAO.buscar(dni);
        if (profesor == null) {
            System.out.println("No existe ningún profesor con ese DNI.");
        } else {
            System.out.println(profesor);
        }
    }
    private static void actualizarProfesor() {
        System.out.print("DNI del profesor: ");
        String dni = SC.nextLine();
        Profesor profesor = PROFESOR_DAO.buscar(dni);
        if (profesor == null) {
            System.out.println("El profesor no existe.");
            return;
        }
        System.out.print("Nuevo nombre: ");
        profesor.setNombre(SC.nextLine());
        System.out.print("Nuevos apellidos: ");
        profesor.setApellidos(SC.nextLine());
        System.out.print("Nuevo NRP: ");
        profesor.setNrp(SC.nextLine());
        System.out.print("Nueva especialidad: ");
        profesor.setEspecialidad(SC.nextLine());
        int idDepartamento =
                leerEntero("Nuevo ID de departamento: ");
        Departamento departamento =
                DEPARTAMENTO_DAO.buscar(idDepartamento);
        if (departamento == null) {
            System.out.println("El departamento no existe.");
            return;
        }
        profesor.setDepartamento(departamento);
        PROFESOR_DAO.actualizar(profesor);
        System.out.println("Profesor actualizado correctamente.");
    }

    private static void eliminarProfesor() {
        System.out.print("DNI del profesor: ");
        String dni = SC.nextLine();
        boolean eliminado = PROFESOR_DAO.eliminar(dni);
        System.out.println(
                eliminado
                        ? "Profesor eliminado correctamente."
                        : "El profesor no existe."
        );
    }

    // =========================================================
    // ADMINISTRATIVOS
    // =========================================================

    private static void menuAdministrativos() {
        int opcion;
        do {
            System.out.println();
            System.out.println("------ GESTIÓN DE ADMINISTRATIVOS ------");
            System.out.println("1. Crear administrativo");
            System.out.println("2. Buscar administrativo");
            System.out.println("3. Actualizar administrativo");
            System.out.println("4. Eliminar administrativo");
            System.out.println("0. Volver");
            opcion = leerEntero("Opción: ");
            try {
                switch (opcion) {
                    case 1 -> crearAdministrativo();
                    case 2 -> buscarAdministrativo();
                    case 3 -> actualizarAdministrativo();
                    case 4 -> eliminarAdministrativo();
                    case 0 -> { }
                    default -> System.out.println("Opción no válida.");
                }
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        } while (opcion != 0);
    }
    private static void crearAdministrativo() {
        System.out.print("DNI: ");
        String dni = SC.nextLine();
        System.out.print("Nombre: ");
        String nombre = SC.nextLine();
        System.out.print("Apellidos: ");
        String apellidos = SC.nextLine();
        System.out.print("NRP: ");
        String nrp = SC.nextLine();
        System.out.print("Área: ");
        String area = SC.nextLine();
        Administrativo administrativo =
                new Administrativo(
                        dni,
                        nombre,
                        apellidos,
                        nrp,
                        area
                );
        ADMINISTRATIVO_DAO.crear(administrativo);
        System.out.println("Administrativo creado correctamente.");
    }

    private static void buscarAdministrativo() {
        System.out.print("DNI: ");
        String dni = SC.nextLine();
        Administrativo administrativo =
                ADMINISTRATIVO_DAO.buscar(dni);
        if (administrativo == null) {
            System.out.println("El administrativo no existe.");
        } else {
            System.out.println(administrativo);
        }
    }
    private static void actualizarAdministrativo() {
        System.out.print("DNI: ");
        String dni = SC.nextLine();
        Administrativo administrativo =
                ADMINISTRATIVO_DAO.buscar(dni);
        if (administrativo == null) {
            System.out.println("El administrativo no existe.");
            return;
        }
        System.out.print("Nuevo nombre: ");
        administrativo.setNombre(SC.nextLine());
        System.out.print("Nuevos apellidos: ");
        administrativo.setApellidos(SC.nextLine());
        System.out.print("Nuevo NRP: ");
        administrativo.setNrp(SC.nextLine());
        System.out.print("Nueva área: ");
        administrativo.setArea(SC.nextLine());
        ADMINISTRATIVO_DAO.actualizar(administrativo);
        System.out.println("Administrativo actualizado correctamente.");
    }
    private static void eliminarAdministrativo() {
        System.out.print("DNI: ");
        String dni = SC.nextLine();
        boolean eliminado =
                ADMINISTRATIVO_DAO.eliminar(dni);
        System.out.println(
                eliminado
                        ? "Administrativo eliminado correctamente."
                        : "El administrativo no existe."
        );
    }
    // =========================================================
    // CURSOS
    // =========================================================
    private static void menuCursos() {
        int opcion;
        do {
            System.out.println();
            System.out.println("----------- GESTIÓN DE CURSOS -----------");
            System.out.println("1. Crear curso");
            System.out.println("2. Buscar curso");
            System.out.println("3. Actualizar curso");
            System.out.println("4. Eliminar curso");
            System.out.println("0. Volver");
            opcion = leerEntero("Opción: ");
            try {
                switch (opcion) {
                    case 1 -> crearCurso();
                    case 2 -> buscarCurso();
                    case 3 -> actualizarCurso();
                    case 4 -> eliminarCurso();
                    case 0 -> { }
                    default -> System.out.println("Opción no válida.");
                }
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }

        } while (opcion != 0);
    }
    private static void crearCurso() {
        int codigo = leerEntero("Código: ");
        System.out.print("Nombre: ");
        String nombre = SC.nextLine();
        System.out.print("Descripción: ");
        String descripcion = SC.nextLine();
        Curso curso =
                new Curso(codigo, nombre, descripcion);
        CURSO_DAO.crear(curso);
        System.out.println("Curso creado correctamente.");
    }
    private static void buscarCurso() {
        int codigo = leerEntero("Código del curso: ");
        Curso curso = CURSO_DAO.buscar(codigo);
        System.out.println(
                curso == null
                        ? "El curso no existe."
                        : curso
        );
    }
    private static void actualizarCurso() {
        int codigo = leerEntero("Código del curso: ");
        Curso curso = CURSO_DAO.buscar(codigo);
        if (curso == null) {
            System.out.println("El curso no existe.");
            return;
        }
        System.out.print("Nuevo nombre: ");
        curso.setNombre(SC.nextLine());
        System.out.print("Nueva descripción: ");
        curso.setDescripcion(SC.nextLine());
        CURSO_DAO.actualizar(curso);
        System.out.println("Curso actualizado correctamente.");
    }

    private static void eliminarCurso() {

        int codigo = leerEntero("Código del curso: ");

        boolean eliminado = CURSO_DAO.eliminar(codigo);

        System.out.println(
                eliminado
                        ? "Curso eliminado correctamente."
                        : "El curso no existe."
        );
    }

    // =========================================================
    // ASIGNATURAS
    // =========================================================
    private static void menuAsignaturas() {
        int opcion;
        do {
            System.out.println();
            System.out.println("-------- GESTIÓN DE ASIGNATURAS --------");
            System.out.println("1. Crear asignatura");
            System.out.println("2. Buscar asignatura");
            System.out.println("3. Actualizar asignatura");
            System.out.println("4. Eliminar asignatura");
            System.out.println("0. Volver");
            opcion = leerEntero("Opción: ");
            try {
                switch (opcion) {
                    case 1 -> crearAsignatura();
                    case 2 -> buscarAsignatura();
                    case 3 -> actualizarAsignatura();
                    case 4 -> eliminarAsignatura();
                    case 0 -> { }
                    default -> System.out.println("Opción no válida.");
                }
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }

        } while (opcion != 0);
    }
    private static void crearAsignatura() {
        int codigo = leerEntero("Código: ");
        System.out.print("Nombre: ");
        String nombre = SC.nextLine();
        System.out.print("Descripción: ");
        String descripcion = SC.nextLine();
        Asignatura asignatura =
                new Asignatura(codigo, nombre, descripcion);
        ASIGNATURA_DAO.crear(asignatura);
        System.out.println("Asignatura creada correctamente.");
    }
    private static void buscarAsignatura() {
        int codigo = leerEntero("Código: ");
        Asignatura asignatura =
                ASIGNATURA_DAO.buscar(codigo);
        System.out.println(
                asignatura == null
                        ? "La asignatura no existe."
                        : asignatura
        );
    }
    private static void actualizarAsignatura() {
        int codigo = leerEntero("Código: ");
        Asignatura asignatura =
                ASIGNATURA_DAO.buscar(codigo);
        if (asignatura == null) {
            System.out.println("La asignatura no existe.");
            return;
        }
        System.out.print("Nuevo nombre: ");
        asignatura.setNombre(SC.nextLine());
        System.out.print("Nueva descripción: ");
        asignatura.setDescripcion(SC.nextLine());
        ASIGNATURA_DAO.actualizar(asignatura);
        System.out.println("Asignatura actualizada correctamente.");
    }
    private static void eliminarAsignatura() {
        int codigo = leerEntero("Código: ");
        boolean eliminado =
                ASIGNATURA_DAO.eliminar(codigo);
        System.out.println(
                eliminado
                        ? "Asignatura eliminada correctamente."
                        : "La asignatura no existe."
        );
    }

    // =========================================================
    // DEPARTAMENTOS
    // =========================================================
    private static void menuDepartamentos() {
        int opcion;
        do {
            System.out.println();
            System.out.println("------- GESTIÓN DE DEPARTAMENTOS -------");
            System.out.println("1. Crear departamento");
            System.out.println("2. Buscar departamento");
            System.out.println("3. Actualizar departamento");
            System.out.println("4. Eliminar departamento");
            System.out.println("0. Volver");
            opcion = leerEntero("Opción: ");
            try {
                switch (opcion) {
                    case 1 -> crearDepartamento();
                    case 2 -> buscarDepartamento();
                    case 3 -> actualizarDepartamento();
                    case 4 -> eliminarDepartamento();
                    case 0 -> { }
                    default -> System.out.println("Opción no válida.");
                }
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        } while (opcion != 0);
    }
    private static void crearDepartamento() {
        System.out.print("Nombre del departamento: ");
        String nombre = SC.nextLine();
        Departamento departamento =
                new Departamento(nombre);
        DEPARTAMENTO_DAO.crear(departamento);
        System.out.println(
                "Departamento creado con ID: "
                        + departamento.getId()
        );
    }
    private static void buscarDepartamento() {
        int id = leerEntero("ID del departamento: ");
        Departamento departamento =
                DEPARTAMENTO_DAO.buscar(id);
        System.out.println(
                departamento == null
                        ? "El departamento no existe."
                        : departamento
        );
    }
    private static void actualizarDepartamento() {
        int id = leerEntero("ID del departamento: ");
        Departamento departamento =
                DEPARTAMENTO_DAO.buscar(id);
        if (departamento == null) {
            System.out.println("El departamento no existe.");
            return;
        }
        System.out.print("Nuevo nombre: ");
        departamento.setNombre(SC.nextLine());
        DEPARTAMENTO_DAO.actualizar(departamento);
        System.out.println("Departamento actualizado correctamente.");
    }
    private static void eliminarDepartamento() {
        int id = leerEntero("ID del departamento: ");
        boolean eliminado =
                DEPARTAMENTO_DAO.eliminar(id);
        System.out.println(
                eliminado
                        ? "Departamento eliminado correctamente."
                        : "El departamento no existe."
        );
    }

    // =========================================================
    // EXPEDIENTES
    // =========================================================
    private static void menuExpedientes() {
        int opcion;
        do {
            System.out.println();
            System.out.println("--------- GESTIÓN DE EXPEDIENTES --------");
            System.out.println("1. Crear expediente");
            System.out.println("2. Buscar expediente");
            System.out.println("3. Actualizar expediente");
            System.out.println("4. Eliminar expediente");
            System.out.println("0. Volver");
            opcion = leerEntero("Opción: ");
            try {
                switch (opcion) {
                    case 1 -> crearExpediente();
                    case 2 -> buscarExpediente();
                    case 3 -> actualizarExpediente();
                    case 4 -> eliminarExpediente();
                    case 0 -> { }
                    default -> System.out.println("Opción no válida.");
                }

            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }

        } while (opcion != 0);
    }
    private static void crearExpediente() {
        System.out.print("DNI del alumno: ");
        String dni = SC.nextLine();
        Alumno alumno = ALUMNO_DAO.buscar(dni);
        if (alumno == null) {
            System.out.println("El alumno no existe.");
            return;
        }
        System.out.print("Observaciones: ");
        String observaciones = SC.nextLine();
        Expediente expediente =
                new Expediente(
                        alumno,
                        LocalDate.now(),
                        observaciones
                );
        EXPEDIENTE_DAO.crear(expediente);
        System.out.println(
                "Expediente creado con ID: "
                        + expediente.getId()
        );
    }

    private static void buscarExpediente() {
        int id = leerEntero("ID del expediente: ");
        Expediente expediente =
                EXPEDIENTE_DAO.buscar(id);
        System.out.println(
                expediente == null
                        ? "El expediente no existe."
                        : expediente
        );
    }
    private static void actualizarExpediente() {
        int id = leerEntero("ID del expediente: ");
        Expediente expediente =
                EXPEDIENTE_DAO.buscar(id);
        if (expediente == null) {
            System.out.println("El expediente no existe.");
            return;
        }
        System.out.print("Nuevas observaciones: ");
        expediente.setObservaciones(SC.nextLine());
        System.out.print(
                "Nueva fecha de apertura (AAAA-MM-DD): "
        );
        String fecha = SC.nextLine();
        expediente.setFechaApertura(
                LocalDate.parse(fecha)
        );
        EXPEDIENTE_DAO.actualizar(expediente);
        System.out.println("Expediente actualizado correctamente.");
    }
    private static void eliminarExpediente() {
        int id = leerEntero("ID del expediente: ");
        boolean eliminado =
                EXPEDIENTE_DAO.eliminar(id);
        System.out.println(
                eliminado
                        ? "Expediente eliminado correctamente."
                        : "El expediente no existe."
        );
    }

    // =========================================================
    // RELACIÓN N:M PROFESOR - ASIGNATURA
    // =========================================================
    private static void menuProfesorAsignatura() {
        int opcion;
        do {
            System.out.println();
            System.out.println("----- PROFESOR - ASIGNATURA (N:M) -----");
            System.out.println("1. Asignar asignatura a profesor");
            System.out.println("2. Quitar asignatura a profesor");
            System.out.println("3. Mostrar asignaturas de un profesor");
            System.out.println("4. Mostrar profesores de una asignatura");
            System.out.println("0. Volver");
            opcion = leerEntero("Opción: ");
            try {
                switch (opcion) {
                    case 1 -> asignarAsignaturaAProfesor();
                    case 2 -> quitarAsignaturaAProfesor();
                    case 3 -> mostrarAsignaturasProfesor();
                    case 4 -> mostrarProfesoresAsignatura();
                    case 0 -> { }
                    default -> System.out.println("Opción no válida.");
                }
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }

        } while (opcion != 0);
    }
    private static void asignarAsignaturaAProfesor() {
        System.out.print("DNI del profesor: ");
        String dni = SC.nextLine();
        int codigo =
                leerEntero("Código de la asignatura: ");
        Transaction tx = null;
        try (Session session = HibernateUtil.openSession()) {
            tx = session.beginTransaction();
            Profesor profesor =
                    session.get(Profesor.class, dni);
            Asignatura asignatura =
                    session.get(Asignatura.class, codigo);
            if (profesor == null) {
                System.out.println("El profesor no existe.");
                tx.rollback();
                return;
            }
            if (asignatura == null) {
                System.out.println("La asignatura no existe.");
                tx.rollback();
                return;
            }
            if (profesor.getAsignaturas().contains(asignatura)) {
                System.out.println(
                        "El profesor ya tiene asignada esa asignatura."
                );
                tx.rollback();
                return;
            }
            profesor.addAsignatura(asignatura);
            tx.commit();
            System.out.println(
                    "Asignatura asignada correctamente al profesor."
            );
        } catch (RuntimeException e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

    private static void quitarAsignaturaAProfesor() {
        System.out.print("DNI del profesor: ");
        String dni = SC.nextLine();
        int codigo =
                leerEntero("Código de la asignatura: ");
        Transaction tx = null;
        try (Session session = HibernateUtil.openSession()) {
            tx = session.beginTransaction();
            Profesor profesor =
                    session.get(Profesor.class, dni);
            Asignatura asignatura =
                    session.get(Asignatura.class, codigo);
            if (profesor == null || asignatura == null) {
                System.out.println(
                        "El profesor o la asignatura no existen."
                );
                tx.rollback();
                return;
            }
            if (!profesor.getAsignaturas().contains(asignatura)) {
                System.out.println(
                        "El profesor no tiene asignada esa asignatura."
                );
                tx.rollback();
                return;
            }
            profesor.removeAsignatura(asignatura);
            tx.commit();
            System.out.println(
                    "Asignatura retirada correctamente."
            );
        } catch (RuntimeException e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            throw e;
        }
    }

    private static void mostrarAsignaturasProfesor() {
        System.out.print("DNI del profesor: ");
        String dni = SC.nextLine();
        try (Session session = HibernateUtil.openSession()) {
            Transaction tx = session.beginTransaction();
            Profesor profesor =
                    session.get(Profesor.class, dni);
            if (profesor == null) {
                System.out.println("El profesor no existe.");
                tx.commit();
                return;
            }
            System.out.println();
            System.out.println(
                    "Asignaturas de " + profesor.getNombre() + ":"
            );
            if (profesor.getAsignaturas().isEmpty()) {
                System.out.println(
                        "El profesor no tiene asignaturas."
                );
            } else {
                profesor.getAsignaturas()
                        .forEach(System.out::println);
            }
            tx.commit();
        }
    }
    private static void mostrarProfesoresAsignatura() {
        int codigo =
                leerEntero("Código de la asignatura: ");
        try (Session session = HibernateUtil.openSession()) {
            Transaction tx = session.beginTransaction();
            Asignatura asignatura =
                    session.get(Asignatura.class, codigo);
            if (asignatura == null) {
                System.out.println("La asignatura no existe.");
                tx.commit();
                return;
            }
            System.out.println();
            System.out.println(
                    "Profesores de " + asignatura.getNombre() + ":"
            );
            if (asignatura.getProfesores().isEmpty()) {
                System.out.println(
                        "La asignatura no tiene profesores."
                );
            } else {
                asignatura.getProfesores()
                        .forEach(System.out::println);
            }

            tx.commit();
        }
    }
    // =========================================================
    // MÉTODOS AUXILIARES
    // =========================================================

    private static int leerEntero(String mensaje) {

        while (true) {

            System.out.print(mensaje);

            try {
                return Integer.parseInt(SC.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(
                        "Debe introducir un número entero."
                );
            }
        }
    }
    private static void alumnosPorCurso() {
        int codigo = leerEntero("Código del curso: ");
        var alumnos = HibernateUtilQueries.alumnosPorCurso(codigo);
        if (alumnos.isEmpty()) {
            System.out.println("No hay alumnos matriculados en ese curso.");
            return;
        }
        System.out.println();
        System.out.println("Alumnos del curso:");
        alumnos.forEach(System.out::println);
    }
    private static void profesoresPorDepartamento() {
        System.out.print("Nombre del departamento: ");
        String nombre = SC.nextLine();
        var profesores = HibernateUtilQueries.profesoresPorDepartamento(nombre);
        if (profesores.isEmpty()) {
            System.out.println("No hay profesores en ese departamento.");
            return;
        }
        System.out.println();
        System.out.println("Profesores del departamento " + nombre + ":");
        profesores.forEach(System.out::println);
    }
    private static void profesoresPorAsignatura() {
        int codigo = leerEntero("Código de la asignatura: ");
        var profesores = HibernateUtilQueries.profesoresPorAsignatura(codigo);
        if (profesores.isEmpty()) {
            System.out.println("No hay profesores asociados a esa asignatura.");
            return;
        }
        System.out.println();
        System.out.println("Profesores de la asignatura:");
        profesores.forEach(System.out::println);
    }
    private static void contarAlumnosPorCurso() {
        var resultados =  HibernateUtilQueries.contarAlumnosPorCurso();
        System.out.println();
        System.out.println("ALUMNOS POR CURSO");
        System.out.println("------------------------------");
        if (resultados.isEmpty()) {
            System.out.println("No existen cursos.");
            return;
        }
        for (Object[] fila : resultados) {
            String nombreCurso = (String) fila[0];
            Long numeroAlumnos = (Long) fila[1];
            System.out.println( nombreCurso + ": "+ numeroAlumnos + " alumno(s)");
        }
    }
    private static void actualizarEspecialidad() {
        System.out.print("DNI del profesor: ");
        String dni = SC.nextLine();
        System.out.print("Nueva especialidad: ");
        String especialidad = SC.nextLine();
        int modificados = HibernateUtilQueries.actualizarEspecialidad(dni,especialidad);
        if (modificados == 0) {
            System.out.println("No existe ningún profesor con ese DNI.");
        } else {
            System.out.println("Especialidad actualizada correctamente.");
        }
    }
    private static void eliminarRelacionProfesorAsignatura() {
        System.out.print("DNI del profesor: "); 
        String dni = SC.nextLine();
        int codigo = leerEntero("Código de la asignatura: ");
        int eliminados = HibernateUtilQueries.eliminarRelacionProfesorAsignatura(dni,codigo);
        if (eliminados == 0) {
            System.out.println("No se ha eliminado ninguna relación.");
            System.out.println("Compruebe que el profesor existe y que tiene asignada esa asignatura."            );
        } else {
            System.out.println("Relación profesor-asignatura eliminada correctamente.");
        }
    }
}

package com.ejemplo.centro.util;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.ejemplo.centro.modelo.Alumno;
import com.ejemplo.centro.modelo.Profesor;
public final class HibernateUtilQueries {
    private HibernateUtilQueries() {}
    public static List<Alumno> alumnosPorCurso(int codigoCurso) {
        try (Session session = HibernateUtil.openSession()) {
            return session.createQuery(
                "select a from Alumno a join a.curso c where c.codigo = :codigo", Alumno.class)
                .setParameter("codigo", codigoCurso)
                .getResultList();
        }
    }
    public static List<Profesor> profesoresPorDepartamento(String nombre) {
        try (Session session = HibernateUtil.openSession()) {
            return session.createQuery(
                "select p from Profesor p join p.departamento d where d.nombre = :nombre", Profesor.class)
                .setParameter("nombre", nombre)
                .getResultList();
        }
    }
    public static List<Profesor> profesoresPorAsignatura(int codigo) {
        try (Session session = HibernateUtil.openSession()) {
            return session.createQuery(
                "select distinct p from Profesor p join p.asignaturas a where a.codigo = :codigo", Profesor.class)
                .setParameter("codigo", codigo)
                .getResultList();
        }
    }
    public static List<Object[]> contarAlumnosPorCurso() {
        try (Session session = HibernateUtil.openSession()) {
            return session.createQuery(
                "select c.nombre, count(a) from Curso c left join c.alumnos a group by c.codigo, c.nombre",
                Object[].class).getResultList();
        }
    }
    public static int actualizarEspecialidad(String dni, String nuevaEspecialidad) {
        Transaction tx = null;
        try (Session session = HibernateUtil.openSession()) {
            tx = session.beginTransaction();
            int n = session.createMutationQuery(
                "update Profesor p set p.especialidad = :esp where p.dni = :dni")
                .setParameter("esp", nuevaEspecialidad)
                .setParameter("dni", dni)
                .executeUpdate();
            tx.commit();
            return n;
        } catch (RuntimeException e) {
            if (tx != null && tx.isActive()) tx.rollback();
            throw e;
        }
    }

    public static int eliminarRelacionProfesorAsignatura(String dni, int codigo) {
        Transaction tx = null;
        try (Session session = HibernateUtil.openSession()) {
            tx = session.beginTransaction();
            Profesor p = session.get(Profesor.class, dni);
            if (p == null) { tx.commit(); return 0; }
            boolean eliminado = p.getAsignaturas().removeIf(a -> a.getCodigo().equals(codigo));
            tx.commit();
            return eliminado ? 1 : 0;
        } catch (RuntimeException e) {
            if (tx != null && tx.isActive()) tx.rollback();
            throw e;
        }
    }
}

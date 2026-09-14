package com.ejemplo.centro.util;
import java.util.List;
import com.ejemplo.centro.modelo.Alumno;
import com.ejemplo.centro.modelo.Profesor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public final class JPAUtilQueries {
    private JPAUtilQueries() {}

    public static List<Alumno> alumnosPorCurso(int codigoCurso) {
        EntityManager em = JPAUtil.createEntityManager();
        try {
            return em.createQuery(
                "select a from Alumno a join a.curso c where c.codigo = :codigo", Alumno.class)
                .setParameter("codigo", codigoCurso)
                .getResultList();
        } finally { em.close(); }
    }
    public static List<Profesor> profesoresPorAsignatura(int codigo) {
        EntityManager em = JPAUtil.createEntityManager();
        try {
            return em.createQuery(
                "select distinct p from Profesor p join p.asignaturas a where a.codigo = :codigo", Profesor.class)
                .setParameter("codigo", codigo)
                .getResultList();
        } finally { em.close(); }
    }
    public static List<Object[]> contarAlumnosPorCurso() {
        EntityManager em = JPAUtil.createEntityManager();
        try {
            return em.createQuery(
                "select c.nombre, count(a) from Curso c left join c.alumnos a group by c.codigo, c.nombre",
                Object[].class).getResultList();
        } finally { em.close(); }
    }

    public static int actualizarEspecialidad(String dni, String especialidad) {
        EntityManager em = JPAUtil.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            int n = em.createQuery(
                "update Profesor p set p.especialidad = :esp where p.dni = :dni")
                .setParameter("esp", especialidad)
                .setParameter("dni", dni)
                .executeUpdate();
            tx.commit();
            return n;
        } catch (RuntimeException e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally { em.close(); }
    }
    public static int eliminarAlumnosDeCurso(int codigoCurso) {
        EntityManager em = JPAUtil.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            int n = em.createQuery(
                "delete from Alumno a where a.curso.codigo = :codigo")
                .setParameter("codigo", codigoCurso)
                .executeUpdate();

            tx.commit();

            return n;

        } catch (RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;

        } finally {
            em.close();
        }
    }
}

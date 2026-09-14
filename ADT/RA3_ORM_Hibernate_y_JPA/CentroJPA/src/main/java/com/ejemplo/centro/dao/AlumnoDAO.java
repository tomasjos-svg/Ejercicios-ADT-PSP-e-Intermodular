package com.ejemplo.centro.dao;
import com.ejemplo.centro.modelo.Alumno;
import com.ejemplo.centro.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
public class AlumnoDAO {
    public void crear(Alumno objeto) {
        EntityManager em = JPAUtil.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(objeto);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
    public Alumno buscar(String id) {
        EntityManager em = JPAUtil.createEntityManager();
        try {
            return em.find(Alumno.class, id);
        } finally {
            em.close();
        }
    }
    public Alumno actualizar(Alumno objeto) {
        EntityManager em = JPAUtil.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Alumno gestionado = em.merge(objeto);
            tx.commit();
            return gestionado;
        } catch (RuntimeException e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
    public boolean eliminar(String id) {
        EntityManager em = JPAUtil.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Alumno objeto = em.find(Alumno.class, id);
            if (objeto == null) { tx.commit(); return false; }
            em.remove(objeto);
            tx.commit();
            return true;
        } catch (RuntimeException e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}

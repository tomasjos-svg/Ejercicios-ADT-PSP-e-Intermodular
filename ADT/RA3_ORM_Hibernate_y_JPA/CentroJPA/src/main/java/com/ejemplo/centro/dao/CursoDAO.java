package com.ejemplo.centro.dao;
import com.ejemplo.centro.modelo.Curso;
import com.ejemplo.centro.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
public class CursoDAO {
    public void crear(Curso objeto) {
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
    public Curso buscar(Integer id) {
        EntityManager em = JPAUtil.createEntityManager();
        try {
            return em.find(Curso.class, id);
        } finally {
            em.close();
        }
    }
    public Curso actualizar(Curso objeto) {
        EntityManager em = JPAUtil.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Curso gestionado = em.merge(objeto);
            tx.commit();
            return gestionado;
        } catch (RuntimeException e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
    public boolean eliminar(Integer id) {
        EntityManager em = JPAUtil.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Curso objeto = em.find(Curso.class, id);
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

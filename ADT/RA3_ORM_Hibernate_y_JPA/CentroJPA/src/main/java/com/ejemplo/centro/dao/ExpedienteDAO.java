package com.ejemplo.centro.dao;
import com.ejemplo.centro.modelo.Expediente;
import com.ejemplo.centro.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
public class ExpedienteDAO {
    public void crear(Expediente objeto) {
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
    public Expediente buscar(Integer id) {
        EntityManager em = JPAUtil.createEntityManager();
        try {
            return em.find(Expediente.class, id);
        } finally {
            em.close();
        }
    }
    public Expediente actualizar(Expediente objeto) {
        EntityManager em = JPAUtil.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Expediente gestionado = em.merge(objeto);
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
            Expediente objeto = em.find(Expediente.class, id);
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

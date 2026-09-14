package com.ejemplo.centro.dao;
import com.ejemplo.centro.modelo.Departamento;
import com.ejemplo.centro.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
public class DepartamentoDAO {
    public void crear(Departamento objeto) {
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
    public Departamento buscar(Integer id) {
        EntityManager em = JPAUtil.createEntityManager();
        try {
            return em.find(Departamento.class, id);
        } finally {
            em.close();
        }
    }
    public Departamento actualizar(Departamento objeto) {
        EntityManager em = JPAUtil.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Departamento gestionado = em.merge(objeto);
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
            Departamento objeto = em.find(Departamento.class, id);
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

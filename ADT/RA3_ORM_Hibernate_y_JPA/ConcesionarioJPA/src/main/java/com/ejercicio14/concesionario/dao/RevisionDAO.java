package com.ejercicio14.concesionario.dao;

import com.ejercicio14.concesionario.modelo.Revision;
import com.ejercicio14.concesionario.util.JPAUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class RevisionDAO {

    public void crear(Revision objeto) {

        EntityManager em = JPAUtil.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {

            tx.begin();

            em.persist(objeto);

            tx.commit();

        } catch (RuntimeException e) {

            if (tx.isActive()) {
                tx.rollback();
            }

            throw e;

        } finally {

            em.close();
        }
    }


    public Revision buscar(Long codigo) {

        EntityManager em = JPAUtil.createEntityManager();

        try {

            return em.find(
                Revision.class,
                codigo
            );

        } finally {

            em.close();
        }
    }


    public Revision actualizar(Revision objeto) {

        EntityManager em = JPAUtil.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {

            tx.begin();

            Revision gestionada =
                em.merge(objeto);

            tx.commit();

            return gestionada;

        } catch (RuntimeException e) {

            if (tx.isActive()) {
                tx.rollback();
            }

            throw e;

        } finally {

            em.close();
        }
    }


    public boolean eliminar(Long codigo) {

        EntityManager em = JPAUtil.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {

            tx.begin();

            Revision objeto =
                em.find(
                    Revision.class,
                    codigo
                );

            if (objeto == null) {

                tx.commit();

                return false;
            }

            em.remove(objeto);

            tx.commit();

            return true;

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
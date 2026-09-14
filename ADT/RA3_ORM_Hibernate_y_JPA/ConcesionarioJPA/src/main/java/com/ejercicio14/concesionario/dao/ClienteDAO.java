package com.ejercicio14.concesionario.dao;

import com.ejercicio14.concesionario.modelo.Cliente;
import com.ejercicio14.concesionario.util.JPAUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class ClienteDAO {

    public void crear(Cliente objeto) {

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


    public Cliente buscar(Long id) {

        EntityManager em = JPAUtil.createEntityManager();

        try {

            return em.find(
                Cliente.class,
                id
            );

        } finally {

            em.close();
        }
    }


    public Cliente actualizar(Cliente objeto) {

        EntityManager em = JPAUtil.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {

            tx.begin();

            Cliente gestionado =
                em.merge(objeto);

            tx.commit();

            return gestionado;

        } catch (RuntimeException e) {

            if (tx.isActive()) {
                tx.rollback();
            }

            throw e;

        } finally {

            em.close();
        }
    }


    public boolean eliminar(Long id) {

        EntityManager em = JPAUtil.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {

            tx.begin();

            Cliente objeto =
                em.find(Cliente.class, id);

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
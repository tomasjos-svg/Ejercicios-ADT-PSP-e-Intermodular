package com.ejercicio14.concesionario.dao;

import com.ejercicio14.concesionario.modelo.Coche;
import com.ejercicio14.concesionario.util.JPAUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class CocheDAO {

    public void crear(Coche objeto) {

        EntityManager em =
            JPAUtil.createEntityManager();

        EntityTransaction tx =
            em.getTransaction();

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


    public Coche buscar(String matricula) {

        EntityManager em =
            JPAUtil.createEntityManager();

        try {

            return em.find(
                Coche.class,
                matricula
            );

        } finally {

            em.close();
        }
    }


    public Coche actualizar(Coche objeto) {

        EntityManager em =
            JPAUtil.createEntityManager();

        EntityTransaction tx =
            em.getTransaction();

        try {

            tx.begin();

            Coche gestionado =
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


    public boolean eliminar(String matricula) {

        EntityManager em =
            JPAUtil.createEntityManager();

        EntityTransaction tx =
            em.getTransaction();

        try {

            tx.begin();

            Coche objeto =
                em.find(
                    Coche.class,
                    matricula
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
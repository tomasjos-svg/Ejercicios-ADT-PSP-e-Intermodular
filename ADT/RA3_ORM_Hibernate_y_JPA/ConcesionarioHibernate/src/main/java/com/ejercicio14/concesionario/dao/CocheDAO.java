package com.ejercicio14.concesionario.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.ejercicio14.concesionario.modelo.Coche;
import com.ejercicio14.concesionario.util.HibernateUtil;

public class CocheDAO {

    public void crear(Coche objeto) {

        Transaction tx = null;

        try (Session session = HibernateUtil.openSession()) {

            tx = session.beginTransaction();

            session.persist(objeto);

            tx.commit();

        } catch (RuntimeException e) {

            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            throw e;
        }
    }

    public Coche buscar(String id) {

        try (Session session = HibernateUtil.openSession()) {

            return session.get(Coche.class, id);
        }
    }

    public Coche actualizar(Coche objeto) {

        Transaction tx = null;

        try (Session session = HibernateUtil.openSession()) {

            tx = session.beginTransaction();

            Coche gestionado = session.merge(objeto);

            tx.commit();

            return gestionado;

        } catch (RuntimeException e) {

            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            throw e;
        }
    }

    public boolean eliminar(String id) {

        Transaction tx = null;

        try (Session session = HibernateUtil.openSession()) {

            tx = session.beginTransaction();

            Coche objeto =
                session.get(Coche.class, id);

            if (objeto == null) {

                tx.commit();
                return false;
            }

            session.remove(objeto);

            tx.commit();

            return true;

        } catch (RuntimeException e) {

            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            throw e;
        }
    }
}
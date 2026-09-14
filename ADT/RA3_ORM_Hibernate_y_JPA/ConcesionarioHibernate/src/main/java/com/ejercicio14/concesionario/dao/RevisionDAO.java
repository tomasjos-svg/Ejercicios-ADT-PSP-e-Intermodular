package com.ejercicio14.concesionario.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ejercicio14.concesionario.modelo.Revision;
import com.ejercicio14.concesionario.util.HibernateUtil;

public class RevisionDAO {

    public void crear(Revision objeto) {

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

    public Revision buscar(Long id) {

        try (Session session = HibernateUtil.openSession()) {

            return session.get(Revision.class, id);
        }
    }

    public Revision actualizar(Revision objeto) {

        Transaction tx = null;

        try (Session session = HibernateUtil.openSession()) {

            tx = session.beginTransaction();

            Revision gestionado =
                session.merge(objeto);

            tx.commit();

            return gestionado;

        } catch (RuntimeException e) {

            if (tx != null && tx.isActive()) {
                tx.rollback();
            }

            throw e;
        }
    }

    public boolean eliminar(Long id) {

        Transaction tx = null;

        try (Session session = HibernateUtil.openSession()) {

            tx = session.beginTransaction();

            Revision objeto =
                session.get(Revision.class, id);

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
package com.ejercicio14.concesionario.dao;


import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ejercicio14.concesionario.modelo.Cliente;
import com.ejercicio14.concesionario.util.HibernateUtil;

public class ClienteDAO {

    public void crear(Cliente objeto) {

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

    public Cliente buscar(Long id) {

        try (Session session = HibernateUtil.openSession()) {

            return session.get(Cliente.class, id);
        }
    }

    public Cliente actualizar(Cliente objeto) {

        Transaction tx = null;

        try (Session session = HibernateUtil.openSession()) {

            tx = session.beginTransaction();

            Cliente gestionado = session.merge(objeto);

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

            Cliente objeto =
                session.get(Cliente.class, id);

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
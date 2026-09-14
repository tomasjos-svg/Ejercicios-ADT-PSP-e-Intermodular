package com.ejemplo.centro.dao;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.ejemplo.centro.modelo.Administrativo;
import com.ejemplo.centro.util.HibernateUtil;
public class AdministrativoDAO {
    public void crear(Administrativo objeto) {
        Transaction tx = null;
        try (Session session = HibernateUtil.openSession()) {
            tx = session.beginTransaction();
            session.persist(objeto);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null && tx.isActive()) tx.rollback();
            throw e;
        }
    }
    public Administrativo buscar(String id) {
        try (Session session = HibernateUtil.openSession()) {
            Transaction tx = session.beginTransaction();
            Administrativo objeto = session.get(Administrativo.class, id);
            tx.commit();
            return objeto;
        }
    }
    public Administrativo actualizar(Administrativo objeto) {
        Transaction tx = null;
        try (Session session = HibernateUtil.openSession()) {
            tx = session.beginTransaction();
            Administrativo gestionado = session.merge(objeto);
            tx.commit();
            return gestionado;
        } catch (RuntimeException e) {
            if (tx != null && tx.isActive()) tx.rollback();
            throw e;
        }
    }
    public boolean eliminar(String id) {
        Transaction tx = null;
        try (Session session = HibernateUtil.openSession()) {
            tx = session.beginTransaction();
            Administrativo objeto = session.get(Administrativo.class, id);
            if (objeto == null) {
                tx.commit();
                return false;
            }
            session.remove(objeto);
            tx.commit();
            return true;
        } catch (RuntimeException e) {
            if (tx != null && tx.isActive()) tx.rollback();
            throw e;
        }
    }
}

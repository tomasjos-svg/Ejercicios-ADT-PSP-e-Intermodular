package com.ejemplo.centro.dao;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.ejemplo.centro.modelo.Asignatura;
import com.ejemplo.centro.util.HibernateUtil;
public class AsignaturaDAO {
    public void crear(Asignatura objeto) {
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
    public Asignatura buscar(Integer id) {
        try (Session session = HibernateUtil.openSession()) {
            Transaction tx = session.beginTransaction();
            Asignatura objeto = session.get(Asignatura.class, id);
            tx.commit();
            return objeto;
        }
    }
    public Asignatura actualizar(Asignatura objeto) {
        Transaction tx = null;
        try (Session session = HibernateUtil.openSession()) {
            tx = session.beginTransaction();
            Asignatura gestionado = session.merge(objeto);
            tx.commit();
            return gestionado;
        } catch (RuntimeException e) {
            if (tx != null && tx.isActive()) tx.rollback();
            throw e;
        }
    }
    public boolean eliminar(Integer id) {
        Transaction tx = null;
        try (Session session = HibernateUtil.openSession()) {
            tx = session.beginTransaction();
            Asignatura objeto = session.get(Asignatura.class, id);
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

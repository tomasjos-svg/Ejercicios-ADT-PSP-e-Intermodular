package com.ejemplo.centro.dao;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.ejemplo.centro.modelo.Curso;
import com.ejemplo.centro.util.HibernateUtil;
public class CursoDAO {
    public void crear(Curso objeto) {
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
    public Curso buscar(Integer id) {
        try (Session session = HibernateUtil.openSession()) {
            Transaction tx = session.beginTransaction();
            Curso objeto = session.get(Curso.class, id);
            tx.commit();
            return objeto;
        }
    }
    public Curso actualizar(Curso objeto) {
        Transaction tx = null;
        try (Session session = HibernateUtil.openSession()) {
            tx = session.beginTransaction();
            Curso gestionado = session.merge(objeto);
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
            Curso objeto = session.get(Curso.class, id);
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

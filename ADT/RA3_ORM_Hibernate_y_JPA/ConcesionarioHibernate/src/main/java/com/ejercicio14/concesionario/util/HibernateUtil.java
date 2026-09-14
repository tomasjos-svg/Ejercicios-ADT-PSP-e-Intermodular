package com.ejercicio14.concesionario.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public final class HibernateUtil {

    private static final SessionFactory SESSION_FACTORY =
            new Configuration()
                    .configure()
                    .buildSessionFactory();

    private HibernateUtil() {
    }

    public static Session openSession() {
        return SESSION_FACTORY.openSession();
    }

    public static void shutdown() {
        SESSION_FACTORY.close();
    }
}
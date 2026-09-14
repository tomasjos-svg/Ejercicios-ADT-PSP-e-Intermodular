package com.ejemplo.centro.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public final class JPAUtil {
    private static final EntityManagerFactory EMF =
        Persistence.createEntityManagerFactory("CentroPU");

    private JPAUtil() {}

    public static EntityManager createEntityManager() {
        return EMF.createEntityManager();
    }

    public static void shutdown() {
        if (EMF.isOpen()) EMF.close();
    }
}

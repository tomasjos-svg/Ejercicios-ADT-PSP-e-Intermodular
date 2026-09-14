package com.ejercicio14.concesionario.util;

import java.math.BigDecimal;
import java.util.List;

import com.ejercicio14.concesionario.modelo.Cliente;
import com.ejercicio14.concesionario.modelo.Coche;
import com.ejercicio14.concesionario.modelo.Revision;

import jakarta.persistence.EntityManager;

public final class JPAUtilQueries {

    private JPAUtilQueries() {
    }


    // =====================================================
    // 1. Clientes que compraron coches SEAT
    //    de más de 20.000 euros
    //    y que no viven en Madrid
    // =====================================================

    public static List<Cliente>
        clientesSeatMas20000NoMadrid() {

        EntityManager em =
            JPAUtil.createEntityManager();

        try {

            return em.createQuery(
                """
                select distinct c
                from Cliente c
                join c.coches co
                where upper(co.marca) = 'SEAT'
                  and co.precioVenta > :precio
                  and upper(c.ciudad) <> 'MADRID'
                """,
                Cliente.class
            )
            .setParameter(
                "precio",
                new BigDecimal("20000")
            )
            .getResultList();

        } finally {

            em.close();
        }
    }


    // =====================================================
    // 2. Revisiones de coches VOLKSWAGEN POLO
    // =====================================================

    public static List<Revision>
        revisionesVolkswagenPolo() {

        EntityManager em =
            JPAUtil.createEntityManager();

        try {

            return em.createQuery(
                """
                select r
                from Revision r
                join r.coche c
                where upper(c.marca) = 'VOLKSWAGEN'
                  and upper(c.modelo) = 'POLO'
                """,
                Revision.class
            )
            .getResultList();

        } finally {

            em.close();
        }
    }


    // =====================================================
    // 3. Clientes que NO han comprado ningún SEAT
    // =====================================================

    public static List<Cliente>
        clientesSinSeat() {

        EntityManager em =
            JPAUtil.createEntityManager();

        try {

            return em.createQuery(
                """
                select c
                from Cliente c
                where not exists (
                    select co
                    from Coche co
                    where co.cliente = c
                      and upper(co.marca) = 'SEAT'
                )
                """,
                Cliente.class
            )
            .getResultList();

        } finally {

            em.close();
        }
    }


    // =====================================================
    // 4. Todos los coches del concesionario
    // =====================================================

    public static List<Coche>
        todosLosCoches() {

        EntityManager em =
            JPAUtil.createEntityManager();

        try {

            return em.createQuery(
                "select c from Coche c",
                Coche.class
            )
            .getResultList();

        } finally {

            em.close();
        }
    }
}
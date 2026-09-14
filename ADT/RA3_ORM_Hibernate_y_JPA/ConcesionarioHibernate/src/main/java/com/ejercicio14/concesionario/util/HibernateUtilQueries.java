package com.ejercicio14.concesionario.util;


import java.util.List;

import org.hibernate.Session;

import com.ejercicio14.concesionario.modelo.Cliente;
import com.ejercicio14.concesionario.modelo.Coche;
import com.ejercicio14.concesionario.modelo.Revision;

public final class HibernateUtilQueries {

    private HibernateUtilQueries() {
    }

    // -----------------------------------------------------
    // CONSULTA 1
    // Clientes que compraron SEAT de más de 20.000 €
    // y que no viven en Madrid
    // -----------------------------------------------------

    public static List<Cliente> clientesSeatMas20000FueraMadrid() {

        try (Session session = HibernateUtil.openSession()) {

            return session.createQuery(
                """
                select distinct c
                from Cliente c
                join c.coches co
                where upper(co.marca) = 'SEAT'
                  and co.precioVenta > 20000
                  and upper(c.ciudad) <> 'MADRID'
                """,
                Cliente.class
            ).getResultList();
        }
    }

    // -----------------------------------------------------
    // CONSULTA 2
    // Revisiones de coches VOLKSWAGEN POLO
    // -----------------------------------------------------

    public static List<Revision> revisionesVolkswagenPolo() {

        try (Session session = HibernateUtil.openSession()) {

            return session.createQuery(
                """
                select r
                from Revision r
                join r.coche co
                where upper(co.marca) = 'VOLKSWAGEN'
                  and upper(co.modelo) = 'POLO'
                """,
                Revision.class
            ).getResultList();
        }
    }

    // -----------------------------------------------------
    // CONSULTA 3
    // Clientes que NO han comprado coches SEAT
    // -----------------------------------------------------

    public static List<Cliente> clientesSinSeat() {

        try (Session session = HibernateUtil.openSession()) {

            return session.createQuery(
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
            ).getResultList();
        }
    }

    // -----------------------------------------------------
    // CONSULTA 4
    // Todos los coches
    // -----------------------------------------------------

    public static List<Coche> todosLosCoches() {

        try (Session session = HibernateUtil.openSession()) {

            return session.createQuery(
                "select c from Coche c",
                Coche.class
            ).getResultList();
        }
    }
}
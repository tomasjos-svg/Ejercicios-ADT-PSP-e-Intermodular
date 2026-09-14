package com.ejercicio14.concesionario.app;



import java.math.BigDecimal;
import java.util.Scanner;

import com.ejercicio14.concesionario.dao.ClienteDAO;
import com.ejercicio14.concesionario.dao.CocheDAO;
import com.ejercicio14.concesionario.dao.RevisionDAO;

import com.ejercicio14.concesionario.modelo.Cliente;
import com.ejercicio14.concesionario.modelo.Coche;
import com.ejercicio14.concesionario.modelo.Revision;

import com.ejercicio14.concesionario.util.HibernateUtil;
import com.ejercicio14.concesionario.util.HibernateUtilQueries;

public class PrincipalHibernate {

    private static final Scanner SC = new Scanner(System.in);

    private static final ClienteDAO CLIENTE_DAO = new ClienteDAO();
    private static final CocheDAO COCHE_DAO = new CocheDAO();
    private static final RevisionDAO REVISION_DAO = new RevisionDAO();


    // =========================================================
    // MAIN
    // =========================================================

    public static void main(String[] args) {

        try {

            // Apartado d)
            // Creamos varios objetos y los insertamos en la BD.
            insertarDatosIniciales();

            // Después mostramos el menú con el CRUD y las consultas HQL.
            ejecutarMenu();

        } catch (Exception e) {

            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();

        } finally {

            SC.close();
            HibernateUtil.shutdown();
        }
    }


    // =========================================================
    // INSERCIÓN DE DATOS INICIALES
    // =========================================================

    private static void insertarDatosIniciales() {

        System.out.println("\n=== INSERTANDO DATOS INICIALES ===");

        // -----------------------------------------------------
        // CLIENTES
        // -----------------------------------------------------

        Cliente cliente1 = new Cliente(
            "11111111A",
            "Ana García",
            "Calle Mayor 10",
            "Toledo",
            "600111111"
        );

        Cliente cliente2 = new Cliente(
            "22222222B",
            "Luis Pérez",
            "Calle Alcalá 20",
            "Madrid",
            "600222222"
        );

        Cliente cliente3 = new Cliente(
            "33333333C",
            "Marta López",
            "Avenida Andalucía 30",
            "Sevilla",
            "600333333"
        );

        CLIENTE_DAO.crear(cliente1);
        CLIENTE_DAO.crear(cliente2);
        CLIENTE_DAO.crear(cliente3);


        // -----------------------------------------------------
        // COCHES
        // -----------------------------------------------------

        Coche coche1 = new Coche(
            "1111AAA",
            "SEAT",
            "LEON",
            "Rojo",
            new BigDecimal("25000")
        );

        coche1.setCliente(cliente1);


        Coche coche2 = new Coche(
            "2222BBB",
            "VOLKSWAGEN",
            "POLO",
            "Azul",
            new BigDecimal("19000")
        );

        coche2.setCliente(cliente2);


        Coche coche3 = new Coche(
            "3333CCC",
            "RENAULT",
            "CLIO",
            "Blanco",
            new BigDecimal("18000")
        );

        coche3.setCliente(cliente3);


        Coche coche4 = new Coche(
            "4444DDD",
            "SEAT",
            "IBIZA",
            "Negro",
            new BigDecimal("17000")
        );

        coche4.setCliente(cliente2);


        COCHE_DAO.crear(coche1);
        COCHE_DAO.crear(coche2);
        COCHE_DAO.crear(coche3);
        COCHE_DAO.crear(coche4);


        // -----------------------------------------------------
        // REVISIONES
        // -----------------------------------------------------

        Revision revision1 = new Revision(
            coche2,
            true,
            true,
            false,
            "Cambio de limpiaparabrisas"
        );

        Revision revision2 = new Revision(
            coche2,
            false,
            true,
            true,
            "Revisión general"
        );

        Revision revision3 = new Revision(
            coche1,
            true,
            false,
            false,
            "Sin otras incidencias"
        );

        REVISION_DAO.crear(revision1);
        REVISION_DAO.crear(revision2);
        REVISION_DAO.crear(revision3);

        System.out.println("Datos iniciales insertados correctamente.");
    }


    // =========================================================
    // MENÚ PRINCIPAL
    // =========================================================

    private static void ejecutarMenu() {

        int opcion;

        do {

            mostrarMenu();

            opcion = Integer.parseInt(SC.nextLine());

            try {

                switch (opcion) {

                    case 1 -> crudCliente();

                    case 2 -> crudCoche();

                    case 3 -> crudRevision();

                    case 4 -> consultaClientesSeat();

                    case 5 -> consultaRevisionesVolkswagenPolo();

                    case 6 -> consultaClientesSinSeat();

                    case 7 -> consultaTodosLosCoches();

                    case 0 -> System.out.println("Fin del programa.");

                    default -> System.out.println("Opción no válida.");
                }

            } catch (Exception e) {

                System.out.println("ERROR: " + e.getMessage());
            }

        } while (opcion != 0);
    }


    private static void mostrarMenu() {

        System.out.println("\n==========================================");
        System.out.println("       CONCESIONARIO - HIBERNATE");
        System.out.println("==========================================");

        System.out.println("1. CRUD Cliente");
        System.out.println("2. CRUD Coche");
        System.out.println("3. CRUD Revisión");

        System.out.println("\n--- CONSULTAS HQL ---");

        System.out.println(
            "4. Clientes con SEAT > 20.000 euros fuera de Madrid"
        );

        System.out.println(
            "5. Revisiones de VOLKSWAGEN POLO"
        );

        System.out.println(
            "6. Clientes que no han comprado SEAT"
        );

        System.out.println(
            "7. Mostrar todos los coches"
        );

        System.out.println("0. Salir");

        System.out.print("\nOpción: ");
    }


    // =========================================================
    // CRUD CLIENTE
    // =========================================================

    private static void crudCliente() {

        System.out.println("\n--- CRUD CLIENTE ---");

        System.out.println("1. Crear cliente");
        System.out.println("2. Buscar cliente");
        System.out.println("3. Actualizar cliente");
        System.out.println("4. Eliminar cliente");

        System.out.print("Opción: ");

        int opcion = Integer.parseInt(SC.nextLine());

        switch (opcion) {

            // -------------------------------------------------
            // CREATE
            // -------------------------------------------------

            case 1 -> {

                System.out.print("NIF: ");
                String nif = SC.nextLine();

                System.out.print("Nombre: ");
                String nombre = SC.nextLine();

                System.out.print("Dirección: ");
                String direccion = SC.nextLine();

                System.out.print("Ciudad: ");
                String ciudad = SC.nextLine();

                System.out.print("Teléfono: ");
                String telefono = SC.nextLine();

                Cliente cliente = new Cliente(
                    nif,
                    nombre,
                    direccion,
                    ciudad,
                    telefono
                );

                CLIENTE_DAO.crear(cliente);

                System.out.println(
                    "Cliente creado con código: "
                    + cliente.getCodigoCliente()
                );
            }


            // -------------------------------------------------
            // READ
            // -------------------------------------------------

            case 2 -> {

                System.out.print("Código del cliente: ");

                Long codigo = Long.parseLong(SC.nextLine());

                Cliente cliente = CLIENTE_DAO.buscar(codigo);

                if (cliente == null) {

                    System.out.println("Cliente no encontrado.");

                } else {

                    System.out.println(cliente);
                }
            }


            // -------------------------------------------------
            // UPDATE
            // -------------------------------------------------

            case 3 -> {

                System.out.print("Código del cliente: ");

                Long codigo = Long.parseLong(SC.nextLine());

                Cliente cliente = CLIENTE_DAO.buscar(codigo);

                if (cliente == null) {

                    System.out.println("Cliente no encontrado.");
                    return;
                }

                System.out.print("Nuevo nombre: ");
                cliente.setNombre(SC.nextLine());

                System.out.print("Nueva dirección: ");
                cliente.setDireccion(SC.nextLine());

                System.out.print("Nueva ciudad: ");
                cliente.setCiudad(SC.nextLine());

                System.out.print("Nuevo teléfono: ");
                cliente.setTelefono(SC.nextLine());

                CLIENTE_DAO.actualizar(cliente);

                System.out.println("Cliente actualizado.");
            }


            // -------------------------------------------------
            // DELETE
            // -------------------------------------------------

            case 4 -> {

                System.out.print("Código del cliente: ");

                Long codigo = Long.parseLong(SC.nextLine());

                boolean eliminado = CLIENTE_DAO.eliminar(codigo);

                if (eliminado) {

                    System.out.println("Cliente eliminado.");

                } else {

                    System.out.println("Cliente no encontrado.");
                }
            }


            default -> System.out.println("Opción no válida.");
        }
    }


    // =========================================================
    // CRUD COCHE
    // =========================================================

    private static void crudCoche() {

        System.out.println("\n--- CRUD COCHE ---");

        System.out.println("1. Crear coche");
        System.out.println("2. Buscar coche");
        System.out.println("3. Actualizar coche");
        System.out.println("4. Eliminar coche");

        System.out.print("Opción: ");

        int opcion = Integer.parseInt(SC.nextLine());

        switch (opcion) {

            // -------------------------------------------------
            // CREATE
            // -------------------------------------------------

            case 1 -> {

                System.out.print("Matrícula: ");
                String matricula = SC.nextLine();

                System.out.print("Marca: ");
                String marca = SC.nextLine();

                System.out.print("Modelo: ");
                String modelo = SC.nextLine();

                System.out.print("Color: ");
                String color = SC.nextLine();

                System.out.print("Precio de venta: ");

                BigDecimal precio =
                    new BigDecimal(SC.nextLine());

                Coche coche = new Coche(
                    matricula,
                    marca,
                    modelo,
                    color,
                    precio
                );

                /*
                 * El coche puede estar todavía sin vender.
                 * Si introducimos 0 no tendrá cliente.
                 */

                System.out.print(
                    "Código del cliente comprador "
                    + "(0 = coche no vendido): "
                );

                Long codigoCliente =
                    Long.parseLong(SC.nextLine());

                if (codigoCliente != 0) {

                    Cliente cliente =
                        CLIENTE_DAO.buscar(codigoCliente);

                    if (cliente == null) {

                        System.out.println(
                            "El cliente no existe."
                        );

                        return;
                    }

                    coche.setCliente(cliente);
                }

                COCHE_DAO.crear(coche);

                System.out.println("Coche creado.");
            }


            // -------------------------------------------------
            // READ
            // -------------------------------------------------

            case 2 -> {

                System.out.print("Matrícula: ");

                String matricula = SC.nextLine();

                Coche coche =
                    COCHE_DAO.buscar(matricula);

                if (coche == null) {

                    System.out.println("Coche no encontrado.");

                } else {

                    System.out.println(coche);
                }
            }


            // -------------------------------------------------
            // UPDATE
            // -------------------------------------------------

            case 3 -> {

                System.out.print("Matrícula: ");

                String matricula = SC.nextLine();

                Coche coche =
                    COCHE_DAO.buscar(matricula);

                if (coche == null) {

                    System.out.println("Coche no encontrado.");
                    return;
                }

                System.out.print("Nueva marca: ");
                coche.setMarca(SC.nextLine());

                System.out.print("Nuevo modelo: ");
                coche.setModelo(SC.nextLine());

                System.out.print("Nuevo color: ");
                coche.setColor(SC.nextLine());

                System.out.print("Nuevo precio: ");

                coche.setPrecioVenta(
                    new BigDecimal(SC.nextLine())
                );

                COCHE_DAO.actualizar(coche);

                System.out.println("Coche actualizado.");
            }


            // -------------------------------------------------
            // DELETE
            // -------------------------------------------------

            case 4 -> {

                System.out.print("Matrícula: ");

                String matricula = SC.nextLine();

                boolean eliminado =
                    COCHE_DAO.eliminar(matricula);

                if (eliminado) {

                    System.out.println("Coche eliminado.");

                } else {

                    System.out.println("Coche no encontrado.");
                }
            }


            default -> System.out.println("Opción no válida.");
        }
    }


    // =========================================================
    // CRUD REVISION
    // =========================================================

    private static void crudRevision() {

        System.out.println("\n--- CRUD REVISIÓN ---");

        System.out.println("1. Crear revisión");
        System.out.println("2. Buscar revisión");
        System.out.println("3. Actualizar revisión");
        System.out.println("4. Eliminar revisión");

        System.out.print("Opción: ");

        int opcion = Integer.parseInt(SC.nextLine());

        switch (opcion) {

            // -------------------------------------------------
            // CREATE
            // -------------------------------------------------

            case 1 -> {

                System.out.print(
                    "Matrícula del coche: "
                );

                String matricula = SC.nextLine();

                Coche coche =
                    COCHE_DAO.buscar(matricula);

                if (coche == null) {

                    System.out.println(
                        "El coche no existe."
                    );

                    return;
                }

                System.out.print(
                    "¿Cambio de filtro? (true/false): "
                );

                boolean filtro =
                    Boolean.parseBoolean(
                        SC.nextLine()
                    );

                System.out.print(
                    "¿Cambio de aceite? (true/false): "
                );

                boolean aceite =
                    Boolean.parseBoolean(
                        SC.nextLine()
                    );

                System.out.print(
                    "¿Cambio de frenos? (true/false): "
                );

                boolean frenos =
                    Boolean.parseBoolean(
                        SC.nextLine()
                    );

                System.out.print("Otros: ");

                String otros = SC.nextLine();

                Revision revision =
                    new Revision(
                        coche,
                        filtro,
                        aceite,
                        frenos,
                        otros
                    );

                REVISION_DAO.crear(revision);

                System.out.println(
                    "Revisión creada con código: "
                    + revision.getCodigoRevision()
                );
            }


            // -------------------------------------------------
            // READ
            // -------------------------------------------------

            case 2 -> {

                System.out.print(
                    "Código de revisión: "
                );

                Long codigo =
                    Long.parseLong(SC.nextLine());

                Revision revision =
                    REVISION_DAO.buscar(codigo);

                if (revision == null) {

                    System.out.println(
                        "Revisión no encontrada."
                    );

                } else {

                    System.out.println(revision);
                }
            }


            // -------------------------------------------------
            // UPDATE
            // -------------------------------------------------

            case 3 -> {

                System.out.print(
                    "Código de revisión: "
                );

                Long codigo =
                    Long.parseLong(SC.nextLine());

                Revision revision =
                    REVISION_DAO.buscar(codigo);

                if (revision == null) {

                    System.out.println(
                        "Revisión no encontrada."
                    );

                    return;
                }

                System.out.print(
                    "¿Cambio de filtro? (true/false): "
                );

                revision.setCambioFiltro(
                    Boolean.parseBoolean(
                        SC.nextLine()
                    )
                );

                System.out.print(
                    "¿Cambio de aceite? (true/false): "
                );

                revision.setCambioAceite(
                    Boolean.parseBoolean(
                        SC.nextLine()
                    )
                );

                System.out.print(
                    "¿Cambio de frenos? (true/false): "
                );

                revision.setCambioFrenos(
                    Boolean.parseBoolean(
                        SC.nextLine()
                    )
                );

                System.out.print("Otros: ");

                revision.setOtros(
                    SC.nextLine()
                );

                REVISION_DAO.actualizar(revision);

                System.out.println(
                    "Revisión actualizada."
                );
            }


            // -------------------------------------------------
            // DELETE
            // -------------------------------------------------

            case 4 -> {

                System.out.print(
                    "Código de revisión: "
                );

                Long codigo =
                    Long.parseLong(SC.nextLine());

                boolean eliminado =
                    REVISION_DAO.eliminar(codigo);

                if (eliminado) {

                    System.out.println(
                        "Revisión eliminada."
                    );

                } else {

                    System.out.println(
                        "Revisión no encontrada."
                    );
                }
            }


            default -> System.out.println("Opción no válida.");
        }
    }


    // =========================================================
    // CONSULTAS HQL
    // =========================================================


    // ---------------------------------------------------------
    // CONSULTA 1
    // Clientes que compraron SEAT de más de 20.000 euros
    // y viven fuera de Madrid
    // ---------------------------------------------------------

    private static void consultaClientesSeat() {

        System.out.println(
            "\n--- CLIENTES CON SEAT > 20.000 EUROS "
            + "FUERA DE MADRID ---"
        );

        var lista =
            HibernateUtilQueries
                .clientesSeatMas20000FueraMadrid();

        if (lista.isEmpty()) {

            System.out.println(
                "No se han encontrado resultados."
            );

        } else {

            lista.forEach(System.out::println);
        }
    }


    // ---------------------------------------------------------
    // CONSULTA 2
    // Revisiones de coches VOLKSWAGEN POLO
    // ---------------------------------------------------------

    private static void consultaRevisionesVolkswagenPolo() {

        System.out.println(
            "\n--- REVISIONES DE VOLKSWAGEN POLO ---"
        );

        var lista =
            HibernateUtilQueries
                .revisionesVolkswagenPolo();

        if (lista.isEmpty()) {

            System.out.println(
                "No se han encontrado resultados."
            );

        } else {

            lista.forEach(System.out::println);
        }
    }


    // ---------------------------------------------------------
    // CONSULTA 3
    // Clientes que no han comprado ningún SEAT
    // ---------------------------------------------------------

    private static void consultaClientesSinSeat() {

        System.out.println(
            "\n--- CLIENTES QUE NO HAN COMPRADO SEAT ---"
        );

        var lista =
            HibernateUtilQueries
                .clientesSinSeat();

        if (lista.isEmpty()) {

            System.out.println(
                "No se han encontrado resultados."
            );

        } else {

            lista.forEach(System.out::println);
        }
    }


    // ---------------------------------------------------------
    // CONSULTA 4
    // Todos los coches del concesionario
    // ---------------------------------------------------------

    private static void consultaTodosLosCoches() {

        System.out.println(
            "\n--- TODOS LOS COCHES DEL CONCESIONARIO ---"
        );

        var lista =
            HibernateUtilQueries
                .todosLosCoches();

        if (lista.isEmpty()) {

            System.out.println(
                "No hay coches en el concesionario."
            );

        } else {

            lista.forEach(System.out::println);
        }
    }
}
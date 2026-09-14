package Hilos35;

public class Peaje {

    private boolean[] puestos;
    private double caja;

    public Peaje(int numeroPuestos) {
        puestos = new boolean[numeroPuestos];
        caja = 0;
    }

    public synchronized int ocuparPuesto() {

        for (int i = 0; i < puestos.length; i++) {

            if (!puestos[i]) {
                puestos[i] = true;

                System.out.println(
                        "Puesto " + (i + 1) + " ocupado"
                );

                return i;
            }
        }

        return -1;
    }

    public synchronized void pagar(
            String vehiculo,
            int puesto,
            double cantidad) {

        caja = caja + cantidad;

        System.out.println(
                vehiculo +
                " paga " + cantidad +
                " euros en el puesto " +
                (puesto + 1) +
                ". Caja total: " + caja
        );
    }

    public synchronized void liberarPuesto(int puesto) {

        puestos[puesto] = false;

        System.out.println(
                "Puesto " + (puesto + 1) + " liberado"
        );
    }

    public synchronized double getCaja() {
        return caja;
    }
}
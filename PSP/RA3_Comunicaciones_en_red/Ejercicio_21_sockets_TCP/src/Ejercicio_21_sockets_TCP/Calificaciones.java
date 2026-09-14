package Ejercicio_21_sockets_TCP;

import java.io.Serializable;

public class Calificaciones implements Serializable {

    private String dni;
    private double psp;
    private double ad;
    private double pmdm;
    private double di;

    public Calificaciones(String dni, double psp, double ad,
                          double pmdm, double di) {
        this.dni = dni;
        this.psp = psp;
        this.ad = ad;
        this.pmdm = pmdm;
        this.di = di;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public double getPsp() {
        return psp;
    }

    public void setPsp(double psp) {
        this.psp = psp;
    }

    public double getAd() {
        return ad;
    }

    public void setAd(double ad) {
        this.ad = ad;
    }

    public double getPmdm() {
        return pmdm;
    }

    public void setPmdm(double pmdm) {
        this.pmdm = pmdm;
    }

    public double getDi() {
        return di;
    }

    public void setDi(double di) {
        this.di = di;
    }

    @Override
    public String toString() {
        return "DNI: " + dni +
               " | PSP: " + psp +
               " | AD: " + ad +
               " | PMDM: " + pmdm +
               " | DI: " + di;
    }
}
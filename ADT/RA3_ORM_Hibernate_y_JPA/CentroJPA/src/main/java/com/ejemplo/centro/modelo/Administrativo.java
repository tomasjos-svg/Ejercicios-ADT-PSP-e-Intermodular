package com.ejemplo.centro.modelo;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "ADMINISTRATIVO")
@PrimaryKeyJoinColumn(name = "DNI")
public class Administrativo extends Persona implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "NRP", nullable = false, unique = true, length = 20)
    private String nrp;

    @Column(name = "AREA", nullable = false, length = 100)
    private String area;

    public Administrativo() {}
    public Administrativo(String dni, String nombre, String apellidos, String nrp, String area) {
        super(dni, nombre, apellidos); this.nrp = nrp; this.area = area;
    }
    public String getNrp() { return nrp; }
    public void setNrp(String nrp) { this.nrp = nrp; }
    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }
    @Override
    public String toString() { return "Administrativo{dni='" + getDni() + "', nrp='" + nrp + "', area='" + area + "'}"; }
}

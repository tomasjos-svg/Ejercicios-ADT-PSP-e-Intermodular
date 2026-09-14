package com.ejercicio14.concesionario.modelo;

import java.io.Serializable;

import jakarta.persistence.*;

@Entity
@Table(name = "REVISION")
public class Revision implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO_REVISION")
    private Long codigoRevision;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "MATRICULA", nullable = false)
    private Coche coche;

    @Column(name = "CAMBIO_FILTRO", nullable = false)
    private boolean cambioFiltro;

    @Column(name = "CAMBIO_ACEITE", nullable = false)
    private boolean cambioAceite;

    @Column(name = "CAMBIO_FRENOS", nullable = false)
    private boolean cambioFrenos;

    @Column(name = "OTROS", length = 255)
    private String otros;

    public Revision() {
    }

    public Revision(Coche coche, boolean cambioFiltro,
                    boolean cambioAceite, boolean cambioFrenos,
                    String otros) {
        this.coche = coche;
        this.cambioFiltro = cambioFiltro;
        this.cambioAceite = cambioAceite;
        this.cambioFrenos = cambioFrenos;
        this.otros = otros;
    }

    public Long getCodigoRevision() {
        return codigoRevision;
    }

    public Coche getCoche() {
        return coche;
    }

    public void setCoche(Coche coche) {
        this.coche = coche;
    }

    public boolean isCambioFiltro() {
        return cambioFiltro;
    }

    public void setCambioFiltro(boolean cambioFiltro) {
        this.cambioFiltro = cambioFiltro;
    }

    public boolean isCambioAceite() {
        return cambioAceite;
    }

    public void setCambioAceite(boolean cambioAceite) {
        this.cambioAceite = cambioAceite;
    }

    public boolean isCambioFrenos() {
        return cambioFrenos;
    }

    public void setCambioFrenos(boolean cambioFrenos) {
        this.cambioFrenos = cambioFrenos;
    }

    public String getOtros() {
        return otros;
    }

    public void setOtros(String otros) {
        this.otros = otros;
    }

    @Override
    public String toString() {
        return "Revision{codigoRevision=" + codigoRevision +
                ", cambioFiltro=" + cambioFiltro +
                ", cambioAceite=" + cambioAceite +
                ", cambioFrenos=" + cambioFrenos +
                ", otros='" + otros + '\'' +
                '}';
    }
}
package com.example.marcelocadiz.app_mineduc.model;

/**
 * Created by marcelo.cadiz on 25/10/2017.
 */

public class Matriculas {

    private String RBD;
    private int cantidad;
    private String nivel;
    private String grado;
    private String ensenianza;
    private String anio;

    public String getRBD() {
        return RBD;
    }

    public void setRBD(String RBD) {
        this.RBD = RBD;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public String getEnsenianza() {
        return ensenianza;
    }

    public void setEnsenianza(String ensenianza) {
        this.ensenianza = ensenianza;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }
}

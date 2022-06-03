package com.company;

public class DatoNumerico extends TareasDeControl{

    private double datoNumerico;

    public DatoNumerico(String descripcion, double datoNumerico) {
        super(true, descripcion);
        this.datoNumerico = datoNumerico;
    }

    public double getDatoNumerico() {
        return datoNumerico;
    }

    public void setDatoNumerico(double datoNumerico) {
        this.datoNumerico = datoNumerico;
    }
}

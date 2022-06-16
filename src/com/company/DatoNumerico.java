package com.company;

import java.io.Serializable;

public class DatoNumerico extends TareasDeControl implements ValidarTarea, Serializable {

    private double datoNumerico;

    public DatoNumerico(String descripcion, double datoNumerico) {
        super(descripcion);
        this.datoNumerico = datoNumerico;
    }

    public DatoNumerico() {
    }

    public double getDatoNumerico() {
        return datoNumerico;
    }

    public void setDatoNumerico(double datoNumerico) {
        this.datoNumerico = datoNumerico;
    }

    @Override
    public boolean Validar() {
        return datoNumerico != 0;
    }

    @Override
    public void Resetear() {
        this.datoNumerico=0.0;
    }

    @Override
    public String toString() {
        return this.getDescripcion() + ": " + getDatoNumerico();
    }
}

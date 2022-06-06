package com.company;

public class DatoNumerico extends TareasDeControl implements ValidarTarea{

    private double datoNumerico;

    public DatoNumerico(String descripcion, double datoNumerico) {
        super(descripcion);
        this.datoNumerico = datoNumerico;
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
    public String toString() {
        return "Dato: " + getDatoNumerico();
    }
}

package com.company;

public class DatoTextual extends TareasDeControl implements ValidarTarea{

    private String datoTextual;

    public DatoTextual(String descripcion, String datoTextual) {
        super(descripcion);
        this.datoTextual = datoTextual;
    }

    public String getDatoTextual() {
        return datoTextual;
    }

    public void setDatoTextual(String datoTextual) {
        this.datoTextual = datoTextual;
    }

    @Override
    public boolean Validar() {
        return datoTextual != null;
    }

    @Override
    public String toString() {
        return "Dato: " + getDatoTextual();
    }
}

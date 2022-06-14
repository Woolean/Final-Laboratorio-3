package com.company;

import java.io.Serializable;

public class DatoTextual extends TareasDeControl implements ValidarTarea, Serializable {

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
        return this.getDescripcion() + ": " + getDatoTextual();
    }
}

package com.company;

public class DatoTextual extends TareasDeControl{

    private String datoTextual;

    public DatoTextual(String descripcion, String datoTextual) {
        super(true, descripcion);
        this.datoTextual = datoTextual;
    }

    public String getDatoTextual() {
        return datoTextual;
    }

    public void setDatoTextual(String datoTextual) {
        this.datoTextual = datoTextual;
    }
}

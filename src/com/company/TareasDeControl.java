package com.company;

public class TareasDeControl {

    private boolean realizado;
    private String descripcion;

    public TareasDeControl(boolean realizado, String descripcion) {
        this.realizado = realizado;
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isRealizado() {
        return realizado;
    }

    public void setRealizado(boolean realizado) {
        this.realizado = realizado;
    }
}

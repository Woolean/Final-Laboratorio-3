package com.company;

import java.io.Serializable;

public class TareasDeControl implements Serializable {

    private String descripcion;

    public TareasDeControl(String descripcion) {
        this.descripcion = descripcion;
    }

    public TareasDeControl() {
        this.descripcion = null;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}


package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TareasDeControl {

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


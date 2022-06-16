package com.company;

import java.io.Serializable;

public class DatoBoolean extends TareasDeControl implements ValidarTarea, Serializable {

    private boolean datoBoolean;

    public DatoBoolean(String descripcion, boolean datoBoolean) {
        super(descripcion);
        this.datoBoolean = datoBoolean;
    }

    public boolean isDatoBoolean() {
        return datoBoolean;
    }

    public void setDatoBoolean(boolean datoBoolean) {
        this.datoBoolean = datoBoolean;
    }

    @Override
    public boolean Validar() {
        return datoBoolean;
    }

    @Override
    public void Resetear() {
        this.datoBoolean=false;
    }

    @Override
    public String toString() {
        if (isDatoBoolean()) {
            return this.getDescripcion() + ": Sí.";
        } else {
            return this.getDescripcion() + ": No.";
        }
    }
}

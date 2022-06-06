package com.company;

public class DatoBoolean extends TareasDeControl implements ValidarTarea{

    private boolean datoBoolean;

    public DatoBoolean(String descripcion, boolean datoBoolean) {
        super(descripcion);
        this.datoBoolean = datoBoolean;
    }

    public boolean isDatoBoolean() {
        return datoBoolean;
    }

    public void setDatoBoolean(boolean datoBoolean) {
        this.datoBoolean= datoBoolean;
    }

    @Override
    public boolean Validar() {
        return datoBoolean;
    }

    @Override
    public String toString() {
        if (isDatoBoolean()) {
            return "Sí. ";
        } else {
            return "No. ";
        }
    }
}

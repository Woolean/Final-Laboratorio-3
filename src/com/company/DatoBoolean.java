package com.company;

public class DatoBoolean extends TareasDeControl{

    private boolean datoBoolean;

    public DatoBoolean(String descripcion, boolean datoBoolean) {
        super(true, descripcion);
        this.datoBoolean = datoBoolean;
    }

    public boolean isDatoBoolean() {
        return datoBoolean;
    }

    public void setDatoBoolean(boolean datoBoolean) {
        this.datoBoolean= datoBoolean;
    }
}

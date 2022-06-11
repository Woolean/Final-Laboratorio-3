package com.company;

public class Enfermedad {
    private String EnfermedadNombre;
    private int RecuperacionenDias;

    public Enfermedad(String enfermedadNombre, int recuperacionenDias) {
        EnfermedadNombre = enfermedadNombre;
        RecuperacionenDias = recuperacionenDias;
    }

    public String getEnfermedadNombre() {
        return EnfermedadNombre;
    }

    public void setEnfermedadNombre(String enfermedadNombre) {
        EnfermedadNombre = enfermedadNombre;
    }

    public int getRecuperacionenDias() {
        return RecuperacionenDias;
    }

    public void setRecuperacionenDias(int recuperacionenDias) {
        RecuperacionenDias = recuperacionenDias;
    }
}

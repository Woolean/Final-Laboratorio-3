package com.company;

import java.time.LocalDate;

public class RegistroDiario {
    private LocalDate fecha;
    private boolean completado;
    private String informacion;


    public RegistroDiario(LocalDate fecha, boolean completado, String informacion) {
        this.fecha = fecha;
        this.completado = completado;
        this.informacion = informacion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public boolean isCompletado() {
        return completado;
    }

    public void setCompletado(boolean completado) {
        this.completado = completado;
    }

    public String getInformacion() {
        return informacion;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }
}

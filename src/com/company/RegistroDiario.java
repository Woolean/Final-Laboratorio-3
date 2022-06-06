package com.company;

import java.time.LocalDate;
import java.util.ArrayList;

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

    public boolean verificarfecha(LocalDate hoy){
        if (this.fecha.equals(hoy)){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean VerificaciondeTareas(ArrayList<TareasDeControl> tareas){
        for (TareasDeControl e : tareas){
            if (!e.isRealizado()){
                this.completado=false;
                return this.completado;
            }
        }
        this.completado=true;
        return  this.completado;
    }

    public void RegistrodeTareas(ArrayList<TareasDeControl> tareas){

            StringBuilder registro=new StringBuilder();
            for (TareasDeControl n : tareas){
                    registro.append("\n");
                    registro.append(n.getDescripcion()+":");
                    //registro.append(n.getValor);
                    registro.append("--------------------");
            }
            this.informacion=registro.toString();
    }
}

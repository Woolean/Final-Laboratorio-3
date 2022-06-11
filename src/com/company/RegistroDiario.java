package com.company;

import java.time.LocalDate;
import java.util.ArrayList;

public class RegistroDiario {
    private LocalDate fecha;
    private boolean completado;
    private String informacion;


    public RegistroDiario(LocalDate fecha) {
        this.fecha = fecha;
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
            if (e instanceof DatoNumerico){
                if (!((DatoNumerico) e).Validar()){
                    this.completado=false;
                    return this.completado;
                }
            }
            else if (e instanceof DatoBoolean){
                if (!((DatoBoolean) e).Validar()){
                    this.completado=false;
                    return this.completado;
                }
            }
            else if (e instanceof DatoTextual){
                if (!((DatoTextual) e).Validar()){
                    this.completado=false;
                    return this.completado;
                }
            }

        }
        this.completado=true;
        return  this.completado;
    }

    public String RegistrodeTareas(ArrayList<TareasDeControl> tareas){

            StringBuilder registro=new StringBuilder();
            for (TareasDeControl n : tareas){
                    registro.append("\n");
                    registro.append(n.getDescripcion()+":");
                if (n instanceof DatoNumerico){
                    registro.append(((DatoNumerico) n).getDatoNumerico());
                }
                else if (n instanceof DatoBoolean){
                    registro.append(((DatoBoolean) n).isDatoBoolean());
                }
                else if (n instanceof DatoTextual){
                    registro.append(((DatoTextual) n).getDatoTextual());
                }
                    registro.append("--------------------");
            }
            return this.informacion=registro.toString();
    }
}

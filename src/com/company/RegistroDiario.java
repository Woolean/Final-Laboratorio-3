package com.company;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class RegistroDiario implements Serializable {
    private LocalDate fecha;
    private boolean completado=false;
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

    public boolean verificarfecha(LocalDate hoy) {
        return this.fecha.equals(hoy);
    }

    public boolean VerificaciondeTareas(ArrayList<TareasDeControl> tareas) {
        for (TareasDeControl e : tareas) {
            if (e instanceof DatoNumerico) {
                if (!((DatoNumerico) e).Validar()) {
                    this.completado = false;
                    return false;
                }
            } else if (e instanceof DatoBoolean) {
                if (!((DatoBoolean) e).Validar()) {
                    this.completado = false;
                    return false;
                }
            } else if (e instanceof DatoTextual) {
                if (!((DatoTextual) e).Validar()) {
                    this.completado = false;
                    return false;
                }
            }

        }
        this.completado = true;
        return true;
    }

    public String RegistrodeTareas(ArrayList<TareasDeControl> tareas) {

        StringBuilder registro = new StringBuilder();
        for (TareasDeControl n : tareas) {
            registro.append("\n");
            registro.append(n.getDescripcion()).append(":");
            if (n instanceof DatoNumerico) {
                registro.append(((DatoNumerico) n).getDatoNumerico());
            } else if (n instanceof DatoBoolean) {
                registro.append(((DatoBoolean) n).isDatoBoolean());
            } else if (n instanceof DatoTextual) {
                registro.append(((DatoTextual) n).getDatoTextual());
            }
            registro.append("--------------------");
        }
        this.informacion=registro.toString();
        return this.informacion;
    }
}

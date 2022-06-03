package com.company;

import java.util.ArrayList;

public class PlanDeControl {

    private String Enfermedad;
    private int Tiempo;
    private ArrayList<TareasDeControl> Tratamientos;

    public PlanDeControl(String enfermedad, int tiempo, ArrayList<TareasDeControl> tratamientos) {
        Enfermedad = enfermedad;
        Tiempo = tiempo;
        Tratamientos = tratamientos;
    }

    public String getEnfermedad() {
        return Enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        Enfermedad = enfermedad;
    }

    public int getTiempo() {
        return Tiempo;
    }

    public void setTiempo(int tiempo) {
        Tiempo = tiempo;
    }

    public ArrayList<TareasDeControl> getTratamientos() {
        return Tratamientos;
    }

    public void setTratamientos(ArrayList<TareasDeControl> tratamientos) {
        Tratamientos = tratamientos;
    }
}

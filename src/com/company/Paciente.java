package com.company;

import java.time.LocalDate;
import java.util.ArrayList;

public class Paciente extends Usuarios {

    private String Enfermedad;
    private String Sintomas;
    private LocalDate FindelTratamiento;
    private LocalDate PrincipiodelTratamiento;
    private ArrayList<RegistroDiario> historial;

    public Paciente(String password, String user, String enfermedad, String sintomas) {
        super(password, user);
        Enfermedad = enfermedad;
        Sintomas = sintomas;
    }

    public String getEnfermedad() {
        return Enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        Enfermedad = enfermedad;
    }

    public String getSintomas() {
        return Sintomas;
    }

    public void setSintomas(String sintomas) {
        Sintomas = sintomas;
    }

    public LocalDate getFindelTratamiento() {
        return FindelTratamiento;
    }

    public void setFindelTratamiento(LocalDate findelTratamiento) {
        FindelTratamiento = findelTratamiento;
    }

    public LocalDate getPrincipiodelTratamiento() {
        return PrincipiodelTratamiento;
    }

    public void setPrincipiodelTratamiento(LocalDate principiodelTratamiento) {
        PrincipiodelTratamiento = principiodelTratamiento;
    }

    public ArrayList<RegistroDiario> getHistorial() {
        return historial;
    }

    public void setHistorial(ArrayList<RegistroDiario> historial) {
        this.historial = historial;
    }
}

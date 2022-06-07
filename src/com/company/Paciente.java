package com.company;

import java.time.LocalDate;
import java.util.ArrayList;

public class Paciente extends Usuarios {

    private String Enfermedad;
    private String Sintomas;
    private PlanDeControl planDeControl=null; //se lo inicializa en null para verificar si le asignaron o no un plan el medico
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

    public PlanDeControl getPlanDeControl() {
        return planDeControl;
    }

    public void setPlanDeControl(PlanDeControl planDeControl) {
        this.planDeControl = planDeControl;
    }

    public String BuscarRegistro(LocalDate fechadelregistro){
        for (RegistroDiario e : this.historial){
            if (e.verificarfecha(fechadelregistro)){
                return e.RegistrodeTareas(this.planDeControl.getTratamientos());
            }
        }
        return null;
    }
}

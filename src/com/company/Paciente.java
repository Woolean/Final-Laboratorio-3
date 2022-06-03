package com.company;

import java.time.LocalDate;
import java.util.ArrayList;

public class Paciente extends Usuarios {

    private String Enfermedad;
    private String Sintomas;
    private LocalDate FindelTratamiento;
    private LocalDate PrincipiodelTratamiento;
    private ArrayList<RegistroDiario> historial;

    public Paciente(String password, String user) {
        super(password, user);
    }
}

package com.company;

import java.io.File;
import java.util.ArrayList;

public class Administrador extends Usuarios implements CrearPlandeControl, Menus {

    private ArrayList<Paciente> pacientes;
    private ArrayList<Medico> medicos;
    private ArrayList<PlanDeControl> planes;
    private ArrayList<TareasDeControl> tareasbase;

    public Administrador(String password, String user, ArrayList<Paciente> pacientes, ArrayList<Medico> medicos, ArrayList<PlanDeControl> planes, ArrayList<TareasDeControl> tareasbase) {
        super(password, user);
        this.pacientes = pacientes;
        this.medicos = medicos;
        this.planes = planes;
        this.tareasbase = tareasbase;
    }

    public void agregarnuevoMedico(Medico mediconuevo) {

    }

    public void agregarnuevoPaciente(Paciente pacientenuevo, File pacientes, String Enfermedad, Medico medicoasignado) {

    }


    @Override
    public void Menu() {

    }
}


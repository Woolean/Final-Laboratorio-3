package com.company;

import java.io.File;
import java.util.ArrayList;

public class Administrador extends Usuarios {



    public Administrador(String password, String user) {
        super(password, user);
    }

    public void agregarnuevoMedico(Medico mediconuevo, File medicos){

    }

    public void agregarnuevoPaciente(Paciente pacientenuevo, File pacientes, String Enfermedad, Medico medicoasignado){

    }

    public void agregarTareasdeContol(){

    }

    public String TareasdeControlExistentes(){
        return null;
    }

    public void eliminarTareasdeControl(){

    }
}

package com.company;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;

public class Sistema {
    ArrayList<Paciente> Pacientes;
    ArrayList<Medico> Medicos;
    ArrayList<Administrador> Administradores;
    ArrayList<TareasDeControl> Tareas;
    ArrayList<PlanDeControl> PlanesdeControl;

    public Sistema(ArrayList<Paciente> pacientes, ArrayList<Medico> medicos, ArrayList<Administrador> administradores, ArrayList<TareasDeControl> tareas, ArrayList<PlanDeControl> planesdeControl) {
        Pacientes = pacientes;
        Medicos = medicos;
        Administradores = administradores;
        Tareas = tareas;
        PlanesdeControl = planesdeControl;
    }


    //usar excepciones seguramente
    public Usuarios iniciarSesion(String nombre, String contraseña){
        for (Paciente e : Pacientes){
            if (e.getUser().equalsIgnoreCase(nombre)){
                if (e.getPassword().equalsIgnoreCase(contraseña)){
                    MensajedeAviso(e);
                    return e;
                }
            }
        }
        for (Medico e : Medicos){
            if (e.getUser().equalsIgnoreCase(nombre)){
                if (e.getPassword().equalsIgnoreCase(contraseña)){
                    MensajedeAvisoMedicos(e);
                    return e;
                }
            }
        }
        for (Administrador e : Administradores){
            if (e.getUser().equalsIgnoreCase(nombre)){
                if (e.getPassword().equalsIgnoreCase(contraseña)){
                    return e;
                }
            }
        }
        return null;
    }


    public void MensajedeAviso(Paciente users){
        boolean mensaje;
        mensaje=users.getHistorial().get(users.getHistorial().size()-1).verificarfecha(LocalDate.now());
        if (mensaje==false){
            if (users.getHistorial().get(users.getHistorial().size()-1).VerificaciondeTareas(users.getPlanDeControl().getTratamientos())){
                System.out.println("No Completo las Tareas Diarias el Dia de ayer");
            }
            else {
                System.out.println("Completo las Tareas Diarias el Dia de ayer");
            }
            users.getHistorial().get(users.getHistorial().size()-1).RegistrodeTareas(users.getPlanDeControl().getTratamientos());
        }
    }

    public void MensajedeAvisoMedicos(Medico medico){
        for (Paciente e : medico.getPacientesAsignados()){
            MensajedeAviso(e);
        }
    }

    public void EjecutarMenu(Usuarios user){
        if (user instanceof Paciente){
            ((Paciente) user).Menu();
        }
        else if(user instanceof  Medico){
            ((Paciente) user).Menu();
        }
        else if(user instanceof  Administrador){
            ((Paciente) user).Menu();
        }
    }
}

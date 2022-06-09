package com.company;

import java.lang.reflect.Array;
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


    //usar excepciones
    public Usuarios iniciarSesion(String nombre, String contraseña){
        for (Paciente e : Pacientes){
            if (e.getUser().equalsIgnoreCase(nombre)){
                if (e.getPassword().equalsIgnoreCase(contraseña)){
                    return e;
                }
            }
        }
        for (Medico e : Medicos){
            if (e.getUser().equalsIgnoreCase(nombre)){
                if (e.getPassword().equalsIgnoreCase(contraseña)){
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


    //ver si es mejor en interfaz
    public String MensajedeAviso(Usuarios users){
        return null;
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

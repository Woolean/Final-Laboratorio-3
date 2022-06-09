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
            if (e.getUsers().equalsIgnoreCase(nombre)){
                if (e.getPasswords().equalsIgnoreCase(contraseña)){
                    if (e.getPlanDeControl()!=null){
                        MensajedeAviso(e);
                    }
                    return e;
                }
            }
        }
        for (Medico e : Medicos){
            if (e.getUsers().equalsIgnoreCase(nombre)){
                if (e.getPasswords().equalsIgnoreCase(contraseña)){
                    MensajedeAvisoMedicos(e);
                    return e;
                }
            }
        }
        for (Administrador e : Administradores){
            if (e.getUsers().equalsIgnoreCase(nombre)){
                if (e.getPasswords().equalsIgnoreCase(contraseña)){
                    return e;
                }
            }
        }
        return null;
    }


    public void MensajedeAviso(Paciente users){
        boolean mensaje;
        //verificar si es el primer dia del tratamiento
        if (users.getHistorial()!=null){
            mensaje=users.getHistorial().get(users.getHistorial().size()-1).verificarfecha(LocalDate.now());
            if (mensaje==false){
                if (users.getHistorial().get(users.getHistorial().size()-1).VerificaciondeTareas(users.getPlanDeControl().getTratamientos())==false){
                    System.out.println(users+": No Completo las Tareas Diarias el Dia de ayer");
                }
                users.getHistorial().get(users.getHistorial().size()-1).RegistrodeTareas(users.getPlanDeControl().getTratamientos());
                RegistroDiario registro=new RegistroDiario(LocalDate.now());
                users.getHistorial().add(registro);
            }
        }
        else {
            RegistroDiario registro=new RegistroDiario(LocalDate.now());
            users.getHistorial().add(registro);
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

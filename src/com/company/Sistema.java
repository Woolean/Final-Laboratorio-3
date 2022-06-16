package com.company;

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
    public Usuarios iniciarSesion(String nombre, String contraseña) {
        for (Paciente e : Pacientes) {
            if (e.getUsers().equalsIgnoreCase(nombre)) {
                if (e.getPasswords().equalsIgnoreCase(contraseña)) {
                    if (e.getPlanDeControl() != null) {
                        MensajedeAviso(e);
                    }
                    return e;
                }
            }
        }
        for (Medico e : Medicos) {
            if (e.getUsers().equalsIgnoreCase(nombre)) {
                if (e.getPasswords().equalsIgnoreCase(contraseña)) {
                    try {
                        MensajedeAvisoMedicos(e);
                    } catch (NullPointerException a) {
                        System.out.println("No tiene ningun Paciente Asignado");
                    }
                    return e;
                }
            }
        }
        for (Administrador e : Administradores) {
            if (e.getUsers().equalsIgnoreCase(nombre)) {
                if (e.getPasswords().equalsIgnoreCase(contraseña)) {
                    return e;
                }
            }
        }
        return null;
    }


    public void MensajedeAviso(Paciente users) {
        boolean mensaje;
        //verificar si es el primer dia del tratamiento
        if (users.getHistorial().size() != 0) {
            mensaje = users.getHistorial().get(users.getHistorial().size() - 1).verificarfecha(LocalDate.now());
            if (mensaje==false) {
                if (users.getHistorial().get(users.getHistorial().size() - 1).VerificaciondeTareas(users.getPlanDeControl().getTratamientos())==false) {
                    System.out.println(users + ": No Completo las Tareas Diarias el Dia de ayer");
                }
                //verifica las tareas si se hicieron
                users.getHistorial().get(users.getHistorial().size() - 1).VerificaciondeTareas(users.getPlanDeControl().getTratamientos());
                //guarda los datos en el registro de cada tarea
                users.getHistorial().get(users.getHistorial().size() - 1).RegistrodeTareas(users.getPlanDeControl().getTratamientos());
                //actualiza el usuario
                users.actualizarArchivo();
                //resetea el plan de control
                for (TareasDeControl e : users.getPlanDeControl().getTratamientos()) {
                    if (e instanceof DatoNumerico) {
                        ((DatoNumerico) e).Resetear();
                    } else if (e instanceof DatoBoolean) {
                        ((DatoBoolean) e).Resetear();
                    } else if (e instanceof DatoTextual) {
                        ((DatoTextual) e).Resetear();
                    }
                }
                //guarda en el archivo y crea el registro de hoy
                RegistroDiario registro = new RegistroDiario(LocalDate.now());
                users.getHistorial().add(registro);
                users.actualizarArchivo();
            }
            else {
            }
        } else {
            RegistroDiario registro = new RegistroDiario(LocalDate.now());
            users.getHistorial().add(registro);
            users.actualizarArchivo();
        }
    }

    public void MensajedeAvisoMedicos(Medico medico) throws NullPointerException {
        medico.ActualizarTodoslosPacientesAsignados(this.Pacientes);//
        for (Paciente e : medico.getPacientesAsignados()) {
            MensajedeAviso(e);
        }
    }

    public void EjecutarMenu(Usuarios user) {
        if (user instanceof Paciente) {
            try {
                ((Paciente) user).Menu();
            } catch (NullPointerException sinplan) {
                System.out.println("Aún No tiene Ningun plan Asignado");
            }
        } else if (user instanceof Medico) {
            try {
                ((Medico) user).Menu();
            } catch (NullPointerException sinpaciente) {
                System.out.println("Aún No tiene Ningun Paciente Asignado");
            }
        } else if (user instanceof Administrador) {
            ((Administrador) user).Menu();
        }
    }
}

package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //persistencia
        ArrayList<Paciente> Pacientes=new ArrayList<>();
        ArrayList<Medico> Medicos=new ArrayList<>();
        ArrayList<Administrador> Administradores=new ArrayList<>();
        ArrayList<TareasDeControl> Tareas=new ArrayList<>();
        ArrayList<String> Enfermedades=new ArrayList<>();
        ArrayList<PlanDeControl> PlanesdeControl=new ArrayList<>();

        //Carga de Datos de prueba:


        Paciente paciente1=new Paciente("contra1", "Pablo", "BRONQUITIS", "dolor de garganta");
        Paciente paciente2=new Paciente("contra11", "Juan", "DOLOR_GARGANTA", "dolor de garganta cuando trago");
        Medico medico1=new Medico("contra3", "Fernando", "Oftamologo");
        Medico medico2=new Medico("contra33", "Julio", "Pediatra");
        DatoNumerico tarea1=new DatoNumerico("Tomar temperatura", 0);
        DatoBoolean tarea2=new DatoBoolean("Tomar Pastilla", false);
        String enfermedad1="BRONQUITIS";
        String enfermedad2="DOLOR_GARGANTA";

        Pacientes.add(paciente1);
        Pacientes.add(paciente2);
        Medicos.add(medico1);
        Medicos.add(medico2);
        Tareas.add(tarea1);
        Tareas.add(tarea2);
        Enfermedades.add(enfermedad1);
        Enfermedades.add(enfermedad2);
        PlanDeControl plan1=new PlanDeControl(enfermedad1, 30, null);
        PlanDeControl plan12=new PlanDeControl(enfermedad2, 15, Tareas);
        PlanesdeControl.add(plan1);
        PlanesdeControl.add(plan12);
        Administrador admin2=new Administrador("contra4", "Mateo", Pacientes, Medicos, PlanesdeControl, Tareas);
        Administradores.add(admin2);
        ////////////////////////////////////////

        Sistema hospital=new Sistema(Pacientes, Medicos, Administradores, Tareas, PlanesdeControl);


        //iniciar sesion
        boolean Seguir=false;
        int j=0;
        Usuarios UserenSesion;

        while (!Seguir){
            Scanner scanner1=new Scanner(System.in);
            String nombre,contrasena;
            System.out.println("Bienvenido");
            System.out.println("Ingrese Nombre de Usuario: ");
            nombre=scanner1.nextLine();
            System.out.println("Ingrese Contrasena: ");
            contrasena=scanner1.nextLine();
            UserenSesion=hospital.iniciarSesion(nombre, contrasena);
            if (UserenSesion!=null){
                Seguir=true;
            }
        }
    }
}

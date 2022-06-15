package com.company;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

    public static void main (String[] args) {

        //persistencia
        ArrayList<Paciente> Pacientes = new ArrayList<>();
        ArrayList<Medico> Medicos = new ArrayList<>();
        ArrayList<Administrador> Administradores = new ArrayList<>();
        ArrayList<TareasDeControl> Tareas = new ArrayList<>();
        ArrayList<Enfermedad> Enfermedades = new ArrayList<>();
        ArrayList<PlanDeControl> PlanesdeControl = new ArrayList<>();

        //Creación de Archivos base
        File filePacientes = new File("files/Pacientes.json");
        File fileMedicos = new File("files/Medicos.json");
        File fileAdmins = new File("files/Administradores.json");
        File fileTareas = new File("files/TareasDeControl.json");
        File fileEnfermedad = new File("files/Enfermedades.json");
        File filePlanes = new File("files/PlanesDeControl.json");

        //Gson builders para que funcione LocalDate
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(TareasDeControl.class, new AbstractTareasdeControl());
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
        Gson gson = gsonBuilder.setPrettyPrinting().create();

        //Serializadores
        SerializadorPacientes ser1 = new SerializadorPacientes();
        SerializadorMedicos ser2 = new SerializadorMedicos();
        SerializadorAdministrador ser3 = new SerializadorAdministrador();
        SerializadorPlanesdeControl ser4 = new SerializadorPlanesdeControl();
        SerializadorTareasdeControl ser5 = new SerializadorTareasdeControl();
        SerializadorEnfermedades ser6 = new SerializadorEnfermedades();

        //Carga de Datos de pruebas
        /*Paciente paciente1=new Paciente("contra1", "Pablo", "Bronquitis", "dolor de garganta");
        Paciente paciente2=new Paciente("contra11", "Juan", "Dolor de Garganta", "dolor de garganta cuando trago");
        Medico medico1=new Medico("contra3", "Fernando", "Oftalmologo");
        Medico medico2=new Medico("contra33", "Julio", "Pediatra");
        medico1.AgregarPaciente(paciente1);
        DatoNumerico tarea1=new DatoNumerico("Tomar temperatura", 0);
        DatoBoolean tarea2=new DatoBoolean("Tomar Pastilla", false);
        Enfermedad enfermedad1=new Enfermedad("Bronquitis", 20);
        Enfermedad enfermedad2=new Enfermedad("Dolor de Garganta", 10);
        Pacientes.add(paciente1);
        Pacientes.add(paciente2);
        Medicos.add(medico1);
        Medicos.add(medico2);
        Tareas.add(tarea1);
        Tareas.add(tarea2);
        Enfermedades.add(enfermedad1);
        Enfermedades.add(enfermedad2);
        PlanDeControl plan1=new PlanDeControl(enfermedad1, null);
        PlanDeControl plan12=new PlanDeControl(enfermedad2,  Tareas);
        PlanesdeControl.add(plan1);
        PlanesdeControl.add(plan12);
        medico1.AsignarPlan(paciente1, plan12);*/

        //serializar
        /*try {
            ser1.Serializar(Pacientes, filePacientes);
            ser2.Serializar(Medicos, fileMedicos);
            ser3.Serializar(Administradores, fileAdmins);
            ser4.Serializar(PlanesdeControl, filePlanes);
            ser5.Serializar(Tareas, fileTareas);
            ser6.Serializar(Enfermedades, fileEnfermedad);
        } catch (IOException e) {
            System.out.println("No se pudo leer/escribir el archivo: " + e.getMessage());
            e.printStackTrace();
        }*/

        //deserializar
        try {
            Pacientes= ser1.Deserializar(filePacientes);
            Medicos= ser2.Deserializar(fileMedicos);
            Administradores= ser3.Deserializar(fileAdmins);
            PlanesdeControl= ser4.Deserializar(filePlanes);
            Tareas= ser5.Deserializar(fileTareas);
            Enfermedades= ser6.Deserializar(fileEnfermedad);
        } catch (IOException e) {
            System.out.println("No se pudo leer/escribir el archivo: " + e.getMessage());
            e.printStackTrace();
        }

        Administrador admin2 = new Administrador("admin123", "admin", Pacientes, Medicos, PlanesdeControl, Tareas, Enfermedades);
        Administradores.add(admin2);

        ////////////////////////////////////////

        Sistema hospital = new Sistema(Pacientes, Medicos, Administradores, Tareas, PlanesdeControl);

        //iniciar sesion
        boolean Seguir = false;
        int j = 0;
        Usuarios UserenSesion;

        while (!Seguir) {
            Scanner scanner1 = new Scanner(System.in);
            String nombre, contrasena;
            System.out.println("Bienvenido");
            System.out.println("Ingrese Nombre de Usuario: ");
            nombre = scanner1.nextLine();
            System.out.println("Ingrese Contraseña: ");
            contrasena = scanner1.nextLine();
            UserenSesion = hospital.iniciarSesion(nombre, contrasena);
            if (UserenSesion != null) {
                hospital.EjecutarMenu(UserenSesion);
                Seguir = true;
            }
        }
    }
}

package com.company;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
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
        //Ya cargado en Json
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

        /*try {
            BufferedReader bufferedReaderPacientes = new BufferedReader(new FileReader(filePacientes));
            Type tipoPaciente = new TypeToken<ArrayList<Paciente>>() {}.getType();
            Pacientes = gson.fromJson(bufferedReaderPacientes, tipoPaciente);
            BufferedReader bufferedReaderMedicos = new BufferedReader(new FileReader(fileMedicos));
            Type tipoMedico = new TypeToken<ArrayList<Medico>>() {}.getType();
            Medicos = gson.fromJson(bufferedReaderMedicos, tipoMedico);
            BufferedReader bufferedReaderTareas = new BufferedReader(new FileReader(fileTareas));
            Type tipoTareas = new TypeToken<ArrayList<TareasDeControl>>() {}.getType();
            Tareas = gson.fromJson(bufferedReaderTareas, tipoTareas);
            BufferedReader bufferedReaderEnfermedades = new BufferedReader(new FileReader(fileEnfermedad));
            Type tipoEnfermedad = new TypeToken<ArrayList<Enfermedad>>() {}.getType();
            Enfermedades = gson.fromJson(bufferedReaderEnfermedades, tipoEnfermedad);
            BufferedReader bufferedReaderPlanes = new BufferedReader(new FileReader(filePlanes));
            Type tipoPlanes = new TypeToken<ArrayList<PlanDeControl>>() {}.getType();
            PlanesdeControl = gson.fromJson(bufferedReaderPlanes, tipoPlanes);

            System.out.println(Tareas.toString());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/


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

        //deserealizar

        try {
            Pacientes= (ArrayList<Paciente>) ser1.Deserializar(filePacientes);
            Medicos= (ArrayList<Medico>) ser2.Deserializar(fileMedicos);
            Administradores= (ArrayList<Administrador>) ser3.Deserializar(fileAdmins);
            PlanesdeControl= (ArrayList<PlanDeControl>) ser4.Deserializar(filePlanes);
            Tareas= (ArrayList<TareasDeControl>) ser5.Deserializar(fileTareas);
            Enfermedades= (ArrayList<Enfermedad>) ser6.Deserializar(fileEnfermedad);
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

        //Guardar los datos de una en los archivos, por si hay que resetearlos
        /*guardar.serializarDatos(Pacientes, filePacientes);
        guardar.serializarDatos(Medicos, fileMedicos);
        guardar.serializarDatos(Tareas, fileTareas);
        guardar.serializarDatos(PlanesdeControl, filePlanes);
        guardar.serializarDatos(Enfermedades, fileEnfermedad);*/
    }
}

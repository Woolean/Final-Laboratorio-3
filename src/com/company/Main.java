package com.company;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //persistencia
        ArrayList<Paciente> Pacientes =new ArrayList<>();
        ArrayList<Medico> Medicos=new ArrayList<>();
        ArrayList<Administrador> Administradores=new ArrayList<>();
        ArrayList<TareasDeControl> Tareas=new ArrayList<>();
        ArrayList<Enfermedad> Enfermedades=new ArrayList<>();
        ArrayList<PlanDeControl> PlanesdeControl=new ArrayList<>();

        //Creación de Archivos base
        File filePacientes = new File("files/Pacientes.json");
        File fileMedicos = new File("files/Medicos.json");
        File fileAdmins = new File("files/Administradores.json");
        File fileTareas = new File("files/TareasDeControl.json");
        File fileEnfermedad = new File("fileS/Enfermedades.json");
        File filePlanes = new File("files/PlanesDeControl.json");

        //Gson builders para que funcione LocalDate
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());
        Gson gson = gsonBuilder.setPrettyPrinting().create();
        GuardadorJson guardar = new GuardadorJson(gsonBuilder, gson);

        //Carga de Datos de pruebas
        //Ya cargado en Json
        /*Paciente paciente1=new Paciente("contra1", "Pablo", "BRONQUITIS", "dolor de garganta");
        Paciente paciente2=new Paciente("contra11", "Juan", "DOLOR_GARGANTA", "dolor de garganta cuando trago");
        Medico medico1=new Medico("contra3", "Fernando", "Oftalmólogo", Tareas);
        Medico medico2=new Medico("contra33", "Julio", "Pediatra", Tareas);
        medico1.AgregarPaciente(paciente1);
        DatoNumerico tarea1=new DatoNumerico("Tomar temperatura", 0);
        DatoBoolean tarea2=new DatoBoolean("Tomar Pastilla", false);
        Enfermedad enfermedad1=new Enfermedad("BRONQUITIS", 20);
        Enfermedad enfermedad2=new Enfermedad("DOLOR_GARGANTA", 10);
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

        try {
            BufferedReader bufferedReaderPacientes = new BufferedReader(new FileReader(filePacientes));
            Type tipoPaciente = new TypeToken<ArrayList<Paciente>>() {}.getType();
            Pacientes = gson.fromJson(bufferedReaderPacientes, tipoPaciente);
            BufferedReader bufferedReaderMedicos = new BufferedReader(new FileReader(fileMedicos));
            Type tipoMedico = new TypeToken<ArrayList<Medico>>() {}.getType();
            Medicos = gson.fromJson(bufferedReaderMedicos, tipoMedico);
            /*BufferedReader bufferedReaderAdmins = new BufferedReader(new FileReader(fileAdmins));
            Type tipoAdmin = new TypeToken<ArrayList<Administrador>>() {}.getType();
            Administradores = gson.fromJson(bufferedReaderAdmins, tipoAdmin);*/
            BufferedReader bufferedReaderTareas = new BufferedReader(new FileReader(fileTareas));
            Type tipoTareas = new TypeToken<ArrayList<TareasDeControl>>() {}.getType();
            Tareas = gson.fromJson(bufferedReaderTareas, tipoTareas);
            BufferedReader bufferedReaderEnfermedades = new BufferedReader(new FileReader(fileEnfermedad));
            Type tipoEnfermedad = new TypeToken<ArrayList<Enfermedad>>() {}.getType();
            Enfermedades = gson.fromJson(bufferedReaderEnfermedades, tipoEnfermedad);
            BufferedReader bufferedReaderPlanes = new BufferedReader(new FileReader(filePlanes));
            Type tipoPlanes = new TypeToken<ArrayList<PlanDeControl>>() {}.getType();
            PlanesdeControl = gson.fromJson(bufferedReaderPlanes, tipoPlanes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Administrador admin2=new Administrador("contra4", "Mateo", Pacientes, Medicos, PlanesdeControl, Tareas, Enfermedades, gson, gsonBuilder, guardar);
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
            System.out.println("Ingrese Contraseña: ");
            contrasena=scanner1.nextLine();
            UserenSesion=hospital.iniciarSesion(nombre, contrasena);
            if (UserenSesion!=null){
                hospital.EjecutarMenu(UserenSesion);
                Seguir=true;
            }
            /*guardar.serializarDatos(Pacientes, filePacientes);
            guardar.serializarDatos(Medicos, fileMedicos);
            guardar.serializarDatos(Administradores, fileAdmins);
            guardar.serializarDatos(Tareas, fileTareas);
            guardar.serializarDatos(PlanesdeControl, filePlanes);
            guardar.serializarDatos(Enfermedades, fileEnfermedad);*/
        }

        /*guardar.serializarDatos(Pacientes, filePacientes);
        guardar.serializarDatos(Medicos, fileMedicos);
        guardar.serializarDatos(Administradores, fileAdmins);
        guardar.serializarDatos(Tareas, fileTareas);
        guardar.serializarDatos(PlanesdeControl, filePlanes);
        guardar.serializarDatos(Enfermedades, fileEnfermedad);*/

        //guardo los nuevos datos en los archivos - Jackson
        /*try {
            mapper.writeValue(filePacientes, Pacientes);
            mapper.writeValue(fileMedicos, Medicos);
            mapper.writeValue(fileAdmins, Administradores);
            mapper.writeValue(fileEnfermedad, Enfermedades);
            mapper.writeValue(filePlanes, PlanesdeControl);
            mapper.writeValue(fileTareas, Tareas);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        ///Guardar en Json los datos de las listas
        //Pacientes
        /*try {
            FileOutputStream jsonPacientes = new FileOutputStream(filePacientes);
            OutputStreamWriter miOutWriter = new OutputStreamWriter(jsonPacientes);
            String json = gson.toJson(Pacientes);
            miOutWriter.append(json);
            miOutWriter.close();
            jsonPacientes.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Medicos
        try {
            FileOutputStream jsonMedicos = new FileOutputStream(fileMedicos);
            OutputStreamWriter miOutWriter = new OutputStreamWriter(jsonMedicos);
            String json = gson.toJson(Medicos);
            miOutWriter.append(json);
            miOutWriter.close();
            jsonMedicos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Administradores
        try {
            FileOutputStream jsonAdmins = new FileOutputStream(fileAdmins);
            OutputStreamWriter miOutWriter = new OutputStreamWriter(jsonAdmins);
            String json = gson.toJson(Administradores);
            miOutWriter.append(json);
            miOutWriter.close();
            jsonAdmins.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Tareas
        try {
            FileOutputStream jsonTareas = new FileOutputStream(fileTareas);
            OutputStreamWriter miOutWriter = new OutputStreamWriter(jsonTareas);
            String json = gson.toJson(Tareas);
            miOutWriter.append(json);
            miOutWriter.close();
            jsonTareas.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Enfermedades
        try {
            FileOutputStream jsonEnfermedad = new FileOutputStream(fileEnfermedad);
            OutputStreamWriter miOutWriter = new OutputStreamWriter(jsonEnfermedad);
            String json = gson.toJson(Enfermedades);
            miOutWriter.append(json);
            miOutWriter.close();
            jsonEnfermedad.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Planes
        try {
            FileOutputStream jsonPlanes = new FileOutputStream(filePlanes);
            OutputStreamWriter miOutWriter = new OutputStreamWriter(jsonPlanes);
            String json = gson.toJson(PlanesdeControl);
            miOutWriter.append(json);
            miOutWriter.close();
            jsonPlanes.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}

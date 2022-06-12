package com.company;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        //persistencia
        ArrayList<Paciente> Pacientes=new ArrayList<>();
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

        //Carga de Datos de pruebas
        //Ya cargado en Json
        /*Paciente paciente1=new Paciente("contra1", "Pablo", "BRONQUITIS", "dolor de garganta");
        Paciente paciente2=new Paciente("contra11", "Juan", "DOLOR_GARGANTA", "dolor de garganta cuando trago");
        Medico medico1=new Medico("contra3", "Fernando", "Oftalmólogo");
        Medico medico2=new Medico("contra33", "Julio", "Pediatra");
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
        Administrador admin2=new Administrador("contra4", "Mateo", Pacientes, Medicos, PlanesdeControl, Tareas, Enfermedades);
        Administradores.add(admin2);
        medico1.AsignarPlan(paciente1, plan12);*/

        //ObjectMapper mapper = new ObjectMapper();
        Gson gson = new Gson();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePacientes));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        //Pasar datos de los json a arraylists y trabajar sobre eso
        /*try {
            //Pacientes = mapper.readValue(filePacientes, mapper.getTypeFactory().constructCollectionType(ArrayList.class, Paciente.class));
            //Medicos = mapper.readValue(fileMedicos, mapper.getTypeFactory().constructCollectionType(ArrayList.class, Medico.class));
            //Administradores = mapper.readValue(fileAdmins, mapper.getTypeFactory().constructCollectionType(ArrayList.class, Administrador.class));
            //Tareas = mapper.readValue(fileTareas, mapper.getTypeFactory().constructCollectionType(ArrayList.class, TareasDeControl.class));
            //Enfermedades = mapper.readValue(fileEnfermedad, mapper.getTypeFactory().constructCollectionType(ArrayList.class, Enfermedad.class));
            //PlanesdeControl = mapper.readValue(filePlanes, mapper.getTypeFactory().constructCollectionType(ArrayList.class, PlanDeControl.class));
        } catch (IOException e) {
            e.printStackTrace();
        }*/


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

        }

        //guardo los nuevos datos en los archivos
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
    }
}

package com.company;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Administrador extends Usuarios implements AdministrarTareas{



    public Administrador(String password, String user) {
        super(password, user);
    }

    public void agregarnuevoMedico(Medico mediconuevo, File medicos){

    }

    public void agregarnuevoPaciente(Paciente pacientenuevo, File pacientes, String Enfermedad, Medico medicoasignado){

    }

//paraadminymedicolomismo
    public TareasDeControl agregarTareasdeContol(ArrayList<TareasDeControl> tareas) {

        String descripcionNueva;
        int opcion;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Elegir Tratamiento");
        for (int i=0; i<tareas.size(); i++){
            System.out.println("Opcion "+i+" "+tareas.get(i).getDescripcion());
        }
        System.out.print("Opción: ");
        opcion = scanner.nextInt();

        if (tareas.get(opcion) instanceof DatoNumerico){
            DatoNumerico dato = new DatoNumerico(tareas.get(opcion).getDescripcion(), 0);
            return dato;
        }
        else if (tareas.get(opcion) instanceof DatoBoolean){
            DatoBoolean dato = new DatoBoolean(tareas.get(opcion).getDescripcion(), false);
            return dato;
        }
        else if (tareas.get(opcion) instanceof DatoTextual){
            DatoTextual dato = new DatoTextual(tareas.get(opcion).getDescripcion(), null);
            return dato;
        }
        return null;
    }
    //paraadminymedicolomismo
    public void EliminarTareas(ArrayList<TareasDeControl> tareas) {

        String descripcionNueva;
        boolean seguir=false;
        int opcion;
        Scanner scanner = new Scanner(System.in);


        while (!seguir) {
            System.out.println("Elegir Tratamiento a Eliminar");
            for (int i = 0; i < tareas.size(); i++) {
                System.out.println("Opcion " + i + " " + tareas.get(i).getDescripcion());
            }
            System.out.print("Opción: ");
            opcion = scanner.nextInt();
            if (opcion < tareas.size()) {
                tareas.remove(opcion);
                seguir=true;
            }
        }

    }
//agrega ademad de agregarla a un plan tambien lo puede agregar a un arraylist con las tareas por defecto
    public TareasDeControl CrearTareaNueva() {

        String descripcionNueva;
        int opcion;
        boolean salir=false;
        DatoNumerico datoNumerico;
        DatoTextual datoTextual;
        DatoBoolean datoBoolean;

        Scanner scanner = new Scanner(System.in);


        while (!salir) {
            System.out.println("Descripción de la tarea: ");
            descripcionNueva = scanner.nextLine();
            System.out.println("Elegir tipo de tarea: \n" +
                    "1 - Dato numérico. \n" +
                    "2 - Dato Textual. \n" +
                    "3 - Dato si/no \n"
            );
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> {
                    datoNumerico = new DatoNumerico(descripcionNueva, 0);
                    return datoNumerico;
                }
                case 2 -> {
                    datoTextual = new DatoTextual(descripcionNueva, null);
                    return datoTextual;
                }
                case 3 -> {
                    datoBoolean = new DatoBoolean(descripcionNueva, false);
                    return datoBoolean;
                }
                default -> {
                    System.out.println("Opcion no Valida");
                }
            }
        }
        return null; //no se como pero hay que usar alguna ecepcion aca seguro
    }
}
//verificarbieneltemadela administracion de tareas
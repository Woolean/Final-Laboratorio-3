package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Administrador extends Usuarios implements AdministraciondeTareasdeControl, Menus {

    private ArrayList<Paciente> pacientes;
    private ArrayList<Medico> medicos;
    private ArrayList<PlanDeControl> planes;
    private ArrayList<TareasDeControl> tareasbase;
    private ArrayList<String> enfermedades;

    public Administrador(String password, String user, ArrayList<Paciente> pacientes, ArrayList<Medico> medicos, ArrayList<PlanDeControl> planes, ArrayList<TareasDeControl> tareasbase) {
        super(password, user);
        this.pacientes = pacientes;
        this.medicos = medicos;
        this.planes = planes;
        this.tareasbase = tareasbase;
    }

    public void agregarnuevoMedico(Medico mediconuevo) {
            this.medicos.add(mediconuevo);
            //persistencia
    }

    public void agregarnuevoPaciente(String Enfermedad, Medico medicoasignado, String nombre, String contrasena, String sintomas) {
        Paciente paciente=new Paciente(contrasena, nombre, Enfermedad, sintomas);
        this.pacientes.add(paciente);
        //persistencia
    }


    @Override
    public void Menu() {
        boolean salir=false;
        int opcion=0;
        Scanner scanner=new Scanner(System.in);
        while (!salir){

            System.out.println("Opcion 1: Ingreso de Pacientes");
            System.out.println("Opcion 2: Ingreso de Profesionales");
            System.out.println("Opcion 3: Administración de Tareas de Control");
            System.out.println("Opcion 4: Administracion de Enfermedades");
            System.out.println("Opcion 5: Salir");

            System.out.printf("Ingrese una Opción: ");
            opcion=scanner.nextInt();

            switch (opcion){
                case 1:
                    String enfermedad;
                    String nombre;
                    String contrasena;
                    String sintomas;
                    int i=0;
                    System.out.println("Ingrese nombre de usuario para el Paciente:");
                    nombre=scanner.nextLine();
                    System.out.println("Ingrese contrasena de usuario para el Paciente:");
                    contrasena=scanner.nextLine();
                    System.out.println("Ingrese Sintomas del Paciente:");
                    sintomas=scanner.nextLine();
                    System.out.println("Seleccione la enfermedad");
                    for (String e : enfermedades){
                        System.out.println("Opcion "+i+": "+e);
                        i++;
                    }
                    System.out.println("Opcion: ");
                    opcion=scanner.nextInt();
                    enfermedad=enfermedades.get(opcion);
                    for (int j = 0; j < medicos.size(); j++) {
                        System.out.println("Opcion " + j + " " + medicos.get(i));
                    }
                    System.out.println("Ingrese la opcion de Medico: ");
                    opcion=scanner.nextInt();

                    agregarnuevoPaciente(enfermedad,medicos.get(opcion), nombre, contrasena, sintomas);
                    break;
                case 2:
                    //persistencia faltaria
                    String nombreMedico;
                    String contrasenaMedico;
                    String Especialisacion;
                    System.out.println("Ingrese nombre de usuario para el Medico:");
                    nombreMedico=scanner.nextLine();
                    System.out.println("Ingrese contrasena de usuario para el Medico:");
                    contrasenaMedico=scanner.nextLine();
                    System.out.println("Ingrese Especialisacion del Medico:");
                    Especialisacion=scanner.nextLine();
                    Medico mediconuevo=new Medico(contrasenaMedico, nombreMedico, Especialisacion);
                    agregarnuevoMedico(mediconuevo);
                    break;
                case 3:
                    //dardealta antes de tiempo o modificar fecha
                    System.out.println("Lista de enfermedades Disponibles");
                    for (Enfermedades e : Enfermedades.values()){
                        System.out.println(e);
                    }
                case 4:
                    //administracion de tareas de control
                    boolean salir2=false;
                    int opcion2=0;
                    Scanner scanner2=new Scanner(System.in);
                    PlanDeControl Planamodificar;
                    for (int k=0; k<planes.size(); k++){
                        System.out.println(planes.get(k));
                    }
                    System.out.println("Ingrese Plan a Modificar: ");
                    opcion2=scanner2.nextInt();
                    Planamodificar=planes.get(opcion2);


                    while (!salir2){
                        System.out.println("Opcion 1: Agregar Tarea Existenete al Plan de Control");
                        System.out.println("Opcion 2: Eliminar Tarea");
                        System.out.println("Opcion 3: Agregar nueva Tarea");
                        System.out.println("Opcion 4: Salir");

                        System.out.printf("Ingrese una Opción: ");
                        opcion2=scanner.nextInt();

                        switch (opcion2){
                            case 1:
                                agregarTareasdeContol(Planamodificar.getTratamientos());
                                //persistir cambio del plan
                                salir=true;
                                break;
                            case 2:
                                EliminarTareas(Planamodificar.getTratamientos());
                                //persistir cambio del plan
                                break;
                            case 3:
                                //crearnuevatarea para la lista así peristimos como base
                                CrearTareaNueva(tareasbase);
                                //persistir la lista de tareas
                            case 4:
                                salir=true;
                            default:
                                System.out.println("Opcion no valida");
                                break;
                        }
                    }
                    break;
                case 5:
                    salir=true;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        }

    }

    //modifica la plantilla del plan de control
    @Override
    public void agregarTareasdeContol(ArrayList<TareasDeControl> tareas) {

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
            tareas.add(dato);
        }
        else if (tareas.get(opcion) instanceof DatoBoolean){
            DatoBoolean dato = new DatoBoolean(tareas.get(opcion).getDescripcion(), false);
            tareas.add(dato);
        }
        else if (tareas.get(opcion) instanceof DatoTextual){
            DatoTextual dato = new DatoTextual(tareas.get(opcion).getDescripcion(), null);
            tareas.add(dato);
        }
    }
    //paraadminymedicolomismo
    @Override
    public void EliminarTareas(ArrayList<TareasDeControl> tareas) {

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
    //agregar a un arraylist con las tareas por defecto y persiste
    @Override
    public void CrearTareaNueva(ArrayList<TareasDeControl> tareas) {

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
                    tareas.add(datoNumerico);
                }
                case 2 -> {
                    datoTextual = new DatoTextual(descripcionNueva, null);
                    tareas.add(datoTextual);
                }
                case 3 -> {
                    datoBoolean = new DatoBoolean(descripcionNueva, false);
                    tareas.add(datoBoolean);
                }
                default -> {
                    System.out.println("Opcion no Valida");
                }
            }
        }
        //no se como pero hay que usar alguna ecepcion aca seguro
    }
}


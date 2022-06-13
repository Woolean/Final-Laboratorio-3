package com.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Administrador extends Usuarios implements AdministraciondeTareasdeControl, Menus, Serializable {

    private ArrayList<Paciente> pacientes;
    private ArrayList<Medico> medicos;
    private ArrayList<PlanDeControl> planes;
    private ArrayList<TareasDeControl> tareasbase;
    private ArrayList<Enfermedad> enfermedades;

    public Administrador(String password, String user, ArrayList<Paciente> pacientes, ArrayList<Medico> medicos, ArrayList<PlanDeControl> planes, ArrayList<TareasDeControl> tareasbase, ArrayList<Enfermedad> enfermedades) {
        super(password, user);
        this.pacientes = pacientes;
        this.medicos = medicos;
        this.planes = planes;
        this.tareasbase = tareasbase;
        this.enfermedades=enfermedades;
    }

    public Administrador() {
    }

    public void agregarnuevoMedico(Medico mediconuevo) {
            this.medicos.add(mediconuevo);
            //persistencia
    }

    public void agregarnuevoPaciente(String Enfermedad, Medico medicoasignado, String nombre, String contrasena, String sintomas) {
        Paciente paciente=new Paciente(contrasena, nombre, Enfermedad, sintomas);
        this.pacientes.add(paciente);
        medicoasignado.AgregarPaciente(paciente);
        //persistencia
    }

    public void agregarEnfermedad(String nombre, int dias) {
        Enfermedad nuevaenfermedad=new Enfermedad(nombre, dias);
        enfermedades.add(nuevaenfermedad);
        PlanDeControl nuevoplan=new PlanDeControl(nuevaenfermedad, null);
        planes.add(nuevoplan);
    }
    public void eliminarEnfermedad(int posicionaeliminar) {
        enfermedades.remove(posicionaeliminar);
        planes.remove(posicionaeliminar);
    }
    public void cambiarRecuperacion(int dias, int posicion) {
        enfermedades.get(posicion).setRecuperacionenDias(dias);
        planes.get(posicion).setTiempo(dias);
    }


    @Override
    public void Menu() {
        boolean salir=false;
        int opcion=0;
        Scanner scanner=new Scanner(System.in);
        while (!salir){

            System.out.println("Opcion 1: Ingreso de Pacientes");
            System.out.println("Opcion 2: Ingreso de Profesionales");
            System.out.println("Opcion 3: Administracion de Enfermedades");
            System.out.println("Opcion 4: Administración de Tareas de Control");
            System.out.println("Opcion 5: Salir");

            System.out.printf("Ingrese una Opción: ");
            opcion=scanner.nextInt();

            switch (opcion){
                case 1:
                    Enfermedad enfermedad;
                    String nombre;
                    String contrasena;
                    String sintomas;
                    int i=0;
                    System.out.println("Ingrese nombre de usuario para el Paciente:");
                    scanner.nextLine();
                    nombre=scanner.nextLine();

                    System.out.println("Ingrese contrasena de usuario para el Paciente:");
                    contrasena=scanner.nextLine();

                    System.out.println("Ingrese Sintomas del Paciente:");
                    sintomas=scanner.nextLine();

                    System.out.println("Seleccione la enfermedad");
                    for (Enfermedad e : this.enfermedades){
                        System.out.println("Opcion "+i+": "+e.getEnfermedadNombre());
                        i++;
                    }
                    System.out.println("Opción 99: Volver al inicio.");
                    System.out.println("Opcion: ");
                    opcion=scanner.nextInt();

                    if (opcion < enfermedades.size() || opcion != 99) {
                        enfermedad=enfermedades.get(opcion);
                        for (int j = 0; j < medicos.size(); j++) {
                            System.out.println("Opcion " + j + " " + medicos.get(j));
                        }
                        System.out.println("Ingrese la opcion de Medico: ");
                        opcion=scanner.nextInt();
                        agregarnuevoPaciente(enfermedad.getEnfermedadNombre(),medicos.get(opcion), nombre, contrasena, sintomas);
                        break;
                    } else {
                        System.out.println("La enfermedad no existe, primero créela.");
                        break;
                    }

                case 2:
                    //persistencia faltaria
                    String nombreMedico;
                    String contrasenaMedico;
                    String Especialisacion;
                    System.out.println("Ingrese nombre de usuario para el Medico:");
                    scanner.nextLine();
                    nombreMedico=scanner.nextLine();
                    System.out.println("Ingrese contrasena de usuario para el Medico:");
                    contrasenaMedico=scanner.nextLine();
                    System.out.println("Ingrese Especialisacion del Medico:");
                    Especialisacion=scanner.nextLine();
                    Medico mediconuevo=new Medico(contrasenaMedico, nombreMedico, Especialisacion);
                    agregarnuevoMedico(mediconuevo);
                    break;
                case 3:
                    //Administracion de enefermedades agregar o eliminar
                    boolean salir3=false;
                    int opcion3=0;
                    Scanner scanner3=new Scanner(System.in);
                    System.out.println("Lista de enfermedades Disponibles");
                    for (Enfermedad e : enfermedades){
                        System.out.println(e.getEnfermedadNombre());
                    }


                    while (!salir3){
                        System.out.println("Opcion 1: Agregar nueva Enfermedad: ");
                        System.out.println("Opcion 2: Eliminar Enfermedad: ");
                        System.out.println("Opcion 3: Modificar dias de Recuperacion");
                        System.out.println("Opcion 4: Salir");

                        System.out.printf("Ingrese una Opción: ");
                        opcion3=scanner.nextInt();

                        switch (opcion3){
                            case 1:
                                String nombreenfermedad;
                                int diasenfermedad;

                                System.out.println("Ingrese el Nombre de la Enfermedad: ");
                                scanner.nextLine();
                                nombreenfermedad=scanner.nextLine();

                                System.out.println("Ingrese el Cantidad estipulada de Dias para la Recuperacion de la Enfermedad: ");
                                diasenfermedad=scanner.nextInt();

                                agregarEnfermedad(nombreenfermedad, diasenfermedad);
                                break;
                            case 2:
                                int opcionenfermedad;
                                System.out.println("Lista de Enfermedades");
                                for (int k=0; k<enfermedades.size(); k++ ){
                                    System.out.println("Enfermedad Numero "+k+" "+enfermedades.get(k).getEnfermedadNombre());
                                }
                                System.out.println("Eliminar Enfermedad Numero: ");
                                scanner.nextLine();
                                opcionenfermedad=scanner.nextInt();

                                eliminarEnfermedad(opcionenfermedad);


                                break;
                            case 3:
                                int opcionenfermedad2;
                                int diasrecu;
                                System.out.println("Lista de Enfermedades");
                                for (int k=0; k<enfermedades.size(); k++ ){
                                    System.out.println("Enfermedad Numero "+k+" "+enfermedades.get(k).getEnfermedadNombre());
                                }
                                System.out.println("Opción 99: Volver al menú.");
                                System.out.println("Cambiar dias de Recuperacion de la Enfermedad Numero: ");
                                scanner.nextLine();
                                opcionenfermedad2=scanner.nextInt();

                                if (opcionenfermedad2 < enfermedades.size() || opcionenfermedad2 != 99) {
                                    System.out.println("Nueva Cantidad de Dias: ");
                                    scanner.nextLine();
                                    diasrecu=scanner.nextInt();

                                    cambiarRecuperacion(diasrecu, opcionenfermedad2);
                                    break;
                                } else {
                                    System.out.println("Volviendo al menú principal... ");
                                    break;
                                }

                            case 4:
                                salir3=true;
                                break;
                            default:
                                System.out.println("Opcion no valida");
                                break;
                        }
                    }
                    break;
                case 4:
                    //administracion de tareas de control
                    boolean salir2=false;
                    int opcion2=0;
                    Scanner scanner2=new Scanner(System.in);
                    PlanDeControl Planamodificar;
                    for (int k=0; k<planes.size(); k++){
                        System.out.println("Opcion "+k+":"+planes.get(k));
                    }
                    System.out.println("Opcion 99: Volver atrás. ");
                    System.out.println("Ingrese Plan a Modificar: ");
                    opcion2=scanner2.nextInt();
                    if (opcion2 != 99){
                        Planamodificar=planes.get(opcion2);
                    } else {
                        break;
                    }

                    while (!salir2){
                        System.out.println("Opcion 1: Agregar Tarea Existente al Plan de Control");
                        System.out.println("Opcion 2: Eliminar Tarea");
                        System.out.println("Opcion 3: Crear nueva Tarea");
                        System.out.println("Opcion 4: Salir");

                        System.out.printf("Ingrese una Opción: ");
                        opcion2=scanner.nextInt();

                        switch (opcion2){
                            case 1:
                                agregarTareasdeContol(Planamodificar.getTratamientos());
                                //persistir cambio del plan
                                break;
                            case 2:
                                EliminarTareas(Planamodificar.getTratamientos());
                                //persistir cambio del plan
                                break;
                            case 3:
                                //crearnuevatarea para la lista así peristimos como base
                                CrearTareaNueva(Planamodificar.getTratamientos());
                                break;
                                //persistir la lista de tareas
                            case 4:
                                salir2=true;
                                break;
                            default:
                                System.out.println("Opcion no valida");
                                break;
                        }
                    }
                    break;
                case 5:
                    salir=true;
                    break;
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
        for (int i=0; i<this.tareasbase.size(); i++){
            System.out.println("Opcion "+i+" "+this.tareasbase.get(i).getDescripcion());
        }
        System.out.print("Opción: ");
        opcion = scanner.nextInt();

        if (this.tareasbase.get(opcion) instanceof DatoNumerico){
            DatoNumerico dato = new DatoNumerico(this.tareasbase.get(opcion).getDescripcion(), 0);
            tareas.add(dato);
        }
        else if (this.tareasbase.get(opcion) instanceof DatoBoolean){
            DatoBoolean dato = new DatoBoolean(this.tareasbase.get(opcion).getDescripcion(), false);
            tareas.add(dato);
        }
        else if (this.tareasbase.get(opcion) instanceof DatoTextual){
            DatoTextual dato = new DatoTextual(this.tareasbase.get(opcion).getDescripcion(), null);
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


            System.out.println("Descripción de la tarea: ");
            descripcionNueva = scanner.nextLine();
            System.out.println("Elegir tipo de tarea: \n" +
                    "1 - Dato numérico. \n" +
                    "2 - Dato Textual. \n" +
                    "3 - Dato si/no\n"
            );
        System.out.println("Opcion: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    datoNumerico = new DatoNumerico(descripcionNueva, 0);
                    this.tareasbase.add(datoNumerico);
                    DatoNumerico datoNumerico2 = new DatoNumerico(descripcionNueva, 0);
                    tareas.add(datoNumerico2);
                    break;
                case 2:
                    datoTextual = new DatoTextual(descripcionNueva, null);
                    this.tareasbase.add(datoTextual);
                    DatoTextual datoTextual2 = new DatoTextual(descripcionNueva, null);
                    tareas.add(datoTextual2);
                    break;
                case 3:
                    datoBoolean = new DatoBoolean(descripcionNueva, false);
                    this.tareasbase.add(datoBoolean);
                    DatoBoolean datoBoolean2 = new DatoBoolean(descripcionNueva, false);
                    tareas.add(datoBoolean2);
                    break;
                default:
                    System.out.println("Opcion no Valida");
                    break;
            }
        //no se como pero hay que usar alguna ecepcion aca seguro
    }
}


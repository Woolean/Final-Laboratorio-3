package com.company;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Administrador extends Usuarios implements AdministraciondeTareasdeControl, Menus, Serializable {

    private ArrayList<Paciente> pacientes;
    private ArrayList<Medico> medicos;
    private ArrayList<PlanDeControl> planes;
    private ArrayList<TareasDeControl> tareasbase;
    private ArrayList<Enfermedad> enfermedades;
    SerializadorPacientes ser1 = new SerializadorPacientes();
    SerializadorMedicos ser2 = new SerializadorMedicos();
    SerializadorAdministrador ser3 = new SerializadorAdministrador();
    SerializadorPlanesdeControl ser4 = new SerializadorPlanesdeControl();
    SerializadorTareasdeControl ser5 = new SerializadorTareasdeControl();
    SerializadorEnfermedades ser6 = new SerializadorEnfermedades();


    public Administrador(String password, String user, ArrayList<Paciente> pacientes, ArrayList<Medico> medicos, ArrayList<PlanDeControl> planes, ArrayList<TareasDeControl> tareasbase, ArrayList<Enfermedad> enfermedades) {
        super(password, user);
        this.pacientes = pacientes;
        this.medicos = medicos;
        this.planes = planes;
        this.tareasbase = tareasbase;
        this.enfermedades = enfermedades;
    }


    public void agregarnuevoMedico(Medico mediconuevo) {
        File fileMedicos = new File("files/Medicos.json");
        medicos.add(mediconuevo);
        try {
            ser2.Serializar(medicos, fileMedicos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void agregarnuevoPaciente(String Enfermedad, Medico medicoAsignado, String nombre, String contrasena, String sintomas) {
        File filePacientes = new File("files/Pacientes.json");
        File fileMedicos = new File("files/Medicos.json");
        Paciente paciente = new Paciente(contrasena, nombre, Enfermedad, sintomas);
        pacientes.add(paciente);
        medicoAsignado.AgregarPaciente(paciente);
        
        for (int i=0; i<medicos.size(); i++){
            if (Objects.equals(medicoAsignado.getUsers(), medicos.get(i).getUsers())){
                medicos.set(i, medicoAsignado);
            }
        }

        try {
            ser1.Serializar(pacientes, filePacientes);
            ser2.Serializar(medicos, fileMedicos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void agregarEnfermedad(String nombre, int dias) {
        File fileEnfermedades = new File("files/Enfermedades.json");
        File filePlanes = new File("files/PlanesDeControl.json");
        Enfermedad nuevaenfermedad = new Enfermedad(nombre, dias);
        enfermedades.add(nuevaenfermedad);
        PlanDeControl nuevoplan = new PlanDeControl(nuevaenfermedad, null);
        planes.add(nuevoplan);

        try {
            ser6.Serializar(enfermedades, fileEnfermedades);
            ser4.Serializar(planes, filePlanes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void eliminarEnfermedad(int posicionaeliminar) {
        File fileEnfermedades = new File("files/Enfermedades.json");
        File filePlanes = new File("files/PlanesDeControl.json");
        enfermedades.remove(posicionaeliminar);
        planes.remove(posicionaeliminar);

        try {
            ser6.Serializar(enfermedades, fileEnfermedades);
            ser4.Serializar(planes, filePlanes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cambiarRecuperacion(int dias, int posicion) {
        File fileEnfermedades = new File("files/Enfermedades.json");
        File filePlanes = new File("files/PlanesDeControl.json");
        enfermedades.get(posicion).setRecuperacionenDias(dias);
        planes.get(posicion).setTiempo(dias);

        try {
            ser6.Serializar(enfermedades, fileEnfermedades);
            ser4.Serializar(planes, filePlanes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void Menu() {
        boolean salir = false;
        int opcion;
        Scanner scanner = new Scanner(System.in);


        while (!salir) {

            System.out.println("Opción 1: Ingreso de Pacientes");
            System.out.println("Opción 2: Ingreso de Profesionales");
            System.out.println("Opción 3: Administración de Enfermedades");
            System.out.println("Opción 4: Administración de Tareas de Control");
            System.out.println("Opción 5: Salir");

            System.out.printf("Ingrese una Opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> {
                    Enfermedad enfermedad;
                    String nombre;
                    String contrasena;
                    String sintomas;
                    int i = 0;
                    System.out.println("Ingrese nombre de usuario para el Paciente:");
                    scanner.nextLine();
                    nombre = scanner.nextLine();
                    System.out.println("Ingrese contraseña de usuario para el Paciente:");
                    contrasena = scanner.nextLine();
                    System.out.println("Ingrese Síntomas del Paciente:");
                    sintomas = scanner.nextLine();
                    System.out.println("Seleccione la enfermedad");
                    for (Enfermedad e : this.enfermedades) {
                        System.out.println("Opción " + i + ": " + e.getEnfermedadNombre());
                        i++;
                    }
                    System.out.println("Opción " + (enfermedades.size() + 1) + ": Volver al inicio.");
                    System.out.println("Opción: ");
                    opcion = scanner.nextInt();
                    if (opcion < enfermedades.size()) {
                        enfermedad = enfermedades.get(opcion);
                        for (int j = 0; j < medicos.size(); j++) {
                            System.out.println("Opción " + j + " " + medicos.get(j));
                        }
                        System.out.println("Opción " + (medicos.size() + 1) + ": Cancelar y volver a inicio.");
                        System.out.println("Ingrese la opción de Medico: ");
                        opcion = scanner.nextInt();
                        if (opcion < medicos.size()){
                            agregarnuevoPaciente(enfermedad.getEnfermedadNombre(), medicos.get(opcion), nombre, contrasena, sintomas);
                        } else {
                            System.out.println("Volviendo al menú principal...");
                        }
                    } else {
                        System.out.println("La enfermedad no existe, primero créela.");
                    }
                }
                case 2 -> {
                    //persistencia faltaria
                    String nombreMedico;
                    String contrasenaMedico;
                    String Especialisacion;
                    System.out.println("Ingrese nombre de usuario para el Medico:");
                    scanner.nextLine();
                    nombreMedico = scanner.nextLine();
                    System.out.println("Ingrese contraseña de usuario para el Medico:");
                    contrasenaMedico = scanner.nextLine();
                    System.out.println("Ingrese especialización del Medico:");
                    Especialisacion = scanner.nextLine();
                    Medico mediconuevo = new Medico(contrasenaMedico, nombreMedico, Especialisacion);
                    agregarnuevoMedico(mediconuevo);
                }
                case 3 -> {
                    //Administracion de enefermedades agregar o eliminar
                    boolean salir3 = false;
                    int opcion3 = 0;
                    Scanner scanner3 = new Scanner(System.in);
                    System.out.println("Lista de enfermedades Disponibles");
                    for (Enfermedad e : enfermedades) {
                        System.out.println(e.getEnfermedadNombre());
                    }
                    while (!salir3) {
                        System.out.println("Opción 1: Agregar nueva Enfermedad: ");
                        System.out.println("Opción 2: Eliminar Enfermedad: ");
                        System.out.println("Opción 3: Modificar dias de Recuperación");
                        System.out.println("Opción 4: Salir");

                        System.out.printf("Ingrese una Opción: ");
                        opcion3 = scanner.nextInt();

                        switch (opcion3) {
                            case 1 -> {
                                String nombreenfermedad;
                                int diasenfermedad;
                                System.out.println("Ingrese el Nombre de la Enfermedad: ");
                                scanner.nextLine();
                                nombreenfermedad = scanner.nextLine();
                                System.out.println("Ingrese el Cantidad estipulada de Dias para la Recuperacion de la Enfermedad: ");
                                diasenfermedad = scanner.nextInt();
                                agregarEnfermedad(nombreenfermedad, diasenfermedad);
                            }
                            case 2 -> {
                                int opcionenfermedad;
                                System.out.println("Lista de Enfermedades");
                                for (int k = 0; k < enfermedades.size(); k++) {
                                    System.out.println("Opción " + k + ": " + enfermedades.get(k).getEnfermedadNombre());
                                }
                                System.out.println("Opción " + (enfermedades.size()+1) + ": Cancelar");
                                System.out.println("Eliminar Enfermedad Numero: ");
                                scanner.nextLine();
                                opcionenfermedad = scanner.nextInt();
                                if (opcionenfermedad < enfermedades.size()){
                                    eliminarEnfermedad(opcionenfermedad);
                                } else {
                                    System.out.println("Volviendo al menú principal...");
                                }
                            }
                            case 3 -> {
                                int opcionenfermedad2;
                                int diasrecu;
                                System.out.println("Lista de Enfermedades");
                                for (int k = 0; k < enfermedades.size(); k++) {
                                    System.out.println("Enfermedad Numero " + k + " " + enfermedades.get(k).getEnfermedadNombre());
                                }
                                System.out.println("Opción " + (enfermedades.size()+1) + ": Cancelar");
                                System.out.println("Cambiar dias de Recuperación de la Enfermedad Numero: ");
                                scanner.nextLine();
                                opcionenfermedad2 = scanner.nextInt();
                                if (opcionenfermedad2 < enfermedades.size()) {
                                    System.out.println("Nueva Cantidad de Dias: ");
                                    scanner.nextLine();
                                    diasrecu = scanner.nextInt();

                                    cambiarRecuperacion(diasrecu, opcionenfermedad2);
                                } else {
                                    System.out.println("Volviendo al menú principal... ");
                                }
                            }
                            case 4 -> salir3 = true;
                            default -> System.out.println("Opción no valida");
                        }
                    }
                }
                case 4 -> {
                    //administracion de tareas de control
                    boolean salir2 = false;
                    int opcion2 = 0;
                    Scanner scanner2 = new Scanner(System.in);
                    PlanDeControl Planamodificar;
                    for (int k = 0; k < planes.size(); k++) {
                        System.out.println("Opción " + k + ":" + planes.get(k));
                    }
                    System.out.println("Opción " + (planes.size()+1) + ": Cancelar");
                    System.out.println("Ingrese Plan a Modificar: ");
                    opcion2 = scanner2.nextInt();
                    if (opcion2 < planes.size()) {
                        Planamodificar = planes.get(opcion2);
                    } else {
                        System.out.println("Volviendo al menú principal...");
                        break;
                    }
                    while (!salir2) {
                        System.out.println("Opción 1: Agregar Tarea Existente al Plan de Control");
                        System.out.println("Opción 2: Eliminar Tarea");
                        System.out.println("Opción 3: Crear nueva Tarea");
                        System.out.println("Opción 4: Salir");

                        System.out.printf("Ingrese una Opción: ");
                        opcion2 = scanner.nextInt();

                        switch (opcion2) {
                            case 1 -> agregarTareasdeControl(Planamodificar.getTratamientos());

                            //persistir cambio del plan
                            case 2 -> EliminarTareas(Planamodificar.getTratamientos());

                            //persistir cambio del plan
                            case 3 ->
                                    //crearnuevatarea para la lista así peristimos como base
                                    CrearTareaNueva(Planamodificar.getTratamientos());

                            //persistir la lista de tareas
                            case 4 -> salir2 = true;
                            default -> System.out.println("Opción no valida");
                        }
                    }
                }
                case 5 -> salir = true;
                default -> System.out.println("Opción no valida");
            }
        }

    }

    //modifica la plantilla del plan de control
    @Override
    public void agregarTareasdeControl(ArrayList<TareasDeControl> tareas) {

        String descripcionNueva;
        int opcion;
        Scanner scanner = new Scanner(System.in);
        File fileTareas = new File("files/TareasDeControl.json");

        System.out.println("Elegir Tratamiento");
        for (int i = 0; i < tareas.size(); i++) {
            System.out.println("Opción " + i + " " + tareas.get(i).getDescripcion());
        }
        System.out.println("Opción " + (tareas.size()+1) + ": Cancelar");
        System.out.print("Opción: ");
        opcion = scanner.nextInt();
        if (opcion < tareas.size()){
            if (tareas.get(opcion) instanceof DatoNumerico) {
                DatoNumerico dato = new DatoNumerico(tareas.get(opcion).getDescripcion(), 0);
                tareas.add(dato);
            } else if (tareas.get(opcion) instanceof DatoBoolean) {
                DatoBoolean dato = new DatoBoolean(tareas.get(opcion).getDescripcion(), false);
                tareas.add(dato);
            } else if (tareas.get(opcion) instanceof DatoTextual) {
                DatoTextual dato = new DatoTextual(tareas.get(opcion).getDescripcion(), null);
                tareas.add(dato);
            }

            try {
                ser5.Serializar(tareas, fileTareas);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("Volviendo al menú principal...");
        }

    }

    //paraadminymedicolomismo
    @Override
    public void EliminarTareas(ArrayList<TareasDeControl> tareas) {

        boolean seguir = false;
        int opcion;
        Scanner scanner = new Scanner(System.in);
        File fileTareas = new File("files/TareasDeControl.json");

        while (!seguir) {
            System.out.println("Elegir Tratamiento a Eliminar");
            for (int i = 0; i < tareas.size(); i++) {
                System.out.println("Opción " + i + " " + tareas.get(i).getDescripcion());
            }
            System.out.println("Opción " + (tareas.size()+1) + ": Cancelar");
            System.out.print("Opción: ");
            opcion = scanner.nextInt();
            if (opcion < tareas.size()) {
                tareas.remove(opcion);
                seguir = true;
            }
        }

        try {
            ser5.Serializar(tareas, fileTareas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //agregar a un arraylist con las tareas por defecto y persiste
    @Override
    public void CrearTareaNueva(ArrayList<TareasDeControl> tareas) {

        String descripcionNueva;
        int opcion;
        boolean salir = false;
        DatoNumerico datoNumerico;
        DatoTextual datoTextual;
        DatoBoolean datoBoolean;
        File fileTareas = new File("files/TareasDeControl.json");

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
            case 1 -> {
                datoNumerico = new DatoNumerico(descripcionNueva, 0);
                this.tareasbase.add(datoNumerico);
                DatoNumerico datoNumerico2 = new DatoNumerico(descripcionNueva, 0);
                tareas.add(datoNumerico2);
            }
            case 2 -> {
                datoTextual = new DatoTextual(descripcionNueva, null);
                this.tareasbase.add(datoTextual);
                DatoTextual datoTextual2 = new DatoTextual(descripcionNueva, null);
                tareas.add(datoTextual2);
            }
            case 3 -> {
                datoBoolean = new DatoBoolean(descripcionNueva, false);
                this.tareasbase.add(datoBoolean);
                DatoBoolean datoBoolean2 = new DatoBoolean(descripcionNueva, false);
                tareas.add(datoBoolean2);
            }
            default -> System.out.println("Opción no Valida");
        }
        //no se como pero hay que usar alguna ecepcion aca seguro
        try {
            ser5.Serializar(tareas, fileTareas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


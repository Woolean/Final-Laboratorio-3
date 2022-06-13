package com.company;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Medico extends Usuarios implements AdministraciondeTareasdeControl, Menus, Serializable {

    private String especializacion;
    private ArrayList<Paciente> pacientesAsignados=new ArrayList<>();
    private ArrayList<PlanDeControl> planes;

    public Medico(String password, String user, String especializacion, ArrayList planes) {
        super(password, user);
        this.especializacion = especializacion;
        this.planes = planes;
    }

    //public Medico(){}

    public String getEspecializacion() {
        return especializacion;
    }

    public void setEspecializacion(String especializacion) {
        this.especializacion = especializacion;
    }

    public ArrayList<Paciente> getPacientesAsignados() {
        return pacientesAsignados;
    }

    public void setPacientesAsignados(ArrayList<Paciente> pacientesAsignados) {
        this.pacientesAsignados = pacientesAsignados;
    }

    public void AgregarPaciente(Paciente pacientesAsignados) {
        this.pacientesAsignados.add(pacientesAsignados);
    }

    public void AsignarPlan(Paciente paciente, PlanDeControl plan){
        paciente.setPlanDeControl(plan);
        paciente.setPrincipiodelTratamiento(LocalDate.now());
        paciente.setFindelTratamiento(LocalDate.now().plusDays(plan.getTiempo()));
    }

    public String VerRegistro(Paciente paciente, LocalDate fechadelregistro){
        return paciente.BuscarRegistro(fechadelregistro);
    }

    //verificar cuando se da de alta, se cambia la fecha del fin del tratamiento en el paciente y se cambia el numero de dias del plan de control
    public void FinalizarPlan(Paciente paciente){
        paciente.setFindelTratamiento(LocalDate.now());
        /*long dias= DAYS.between(paciente.getPrincipiodelTratamiento(), LocalDate.now());
        paciente.getPlanDeControl().setTiempo((int) dias); se usaba para cambiar a los dias que duro el tratamiento el plan de control*/
    }

    public Paciente buscarpaciente(String nombre){
        for (Paciente e : this.pacientesAsignados){
            if (nombre.equalsIgnoreCase(e.getUsers())){
                return e;
            }
        }
        return null;
    }

    @Override
    public void Menu() throws NullPointerException{
        boolean salir=false;
        int opcion=0;
        Scanner scanner=new Scanner(System.in);
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.setPrettyPrinting().create();
        GuardadorJson guardar = new GuardadorJson(gsonBuilder, gson);
        File fileTareas = new File("files/TareasDeControl.json");

        while (!salir){

            System.out.println("Opción 1: Asignación de Planes de Control");
            System.out.println("Opción 2: Control de los registros de los Pacientes");
            System.out.println("Opción 3: Finalización de Planes de Control");
            System.out.println("Opción 4: Salir");

            System.out.printf("Ingrese una Opción: ");
            opcion=scanner.nextInt();

            switch (opcion){
                case 1:
                    boolean salir2=false;
                    int opcion2=0;
                    String userpaciente;
                    Paciente asignar;
                    PlanDeControl Planamodificar;
                    Scanner scanner2=new Scanner(System.in);

                    //selecciona el user

                    System.out.println("Pacientes sin Plan de Control:");
                    for (int i=0; i<pacientesAsignados.size(); i++){
                        if (pacientesAsignados.get(i).getPlanDeControl()==null){
                            System.out.println(pacientesAsignados.get(i));
                        }
                    }
                    System.out.println("Ingrese el Usuario: ");
                    userpaciente=scanner2.nextLine();
                    asignar=buscarpaciente(userpaciente);

                    //Opcion 1: levanta los planes base de un archivo y los lee, para elegir uno y hacer una copia


                   /* boolean salir2=false;
                    int opcion2=0;
                    Scanner scanner2=new Scanner(System.in);
                    for (int k=0; k<planes.size(); k++){
                        System.out.println("Opcion "+k+":"+planes.get(k));
                    }
                    System.out.println("Ingrese Plan a Modificar: ");
                    opcion2=scanner2.nextInt();
                    //copia de la enfermeda porque si no la modifica
                    Enfermedad EnfermedadModificar=new Enfermedad(plan.get(opcion2).getEnfermedad.getNombre, plan.get(opcion2).getEnfermedad.getDiasdeRecuperacion);
                    Planamodificar=new PlanDeControl(plan.get(opcion2).getEnfermedad, plan.get(opcion2).getTratamientos);
                    */
                    //dato de prueba
                    Enfermedad nueva=new Enfermedad();
                    Planamodificar=new PlanDeControl(nueva, null);


                    //administracion de tareas de control y dias del plan de control.


                    while (!salir2){
                        System.out.println("SI NO QUIERE HACER MODIFICACIONES EN EL PLAN BASE DE LA ENFERMEDAD SELECCIONE SALIR OPCIÓN 5:");
                        System.out.println("Plan Base:");
                        //System.out.println(Planamodificar);
                        System.out.println("Opción 1: Agregar Tarea Existente al Plan de Control");
                        System.out.println("Opción 2: Eliminar Tarea");
                        System.out.println("Opción 3: Crear nueva Tarea");
                        System.out.println("Opción 4: Cambiar cantidad de dias del Plan");
                        System.out.println("Opción 5: Salir");

                        System.out.printf("Ingrese una Opción: ");
                        opcion2=scanner.nextInt();

                        switch (opcion2) {
                            case 1 -> agregarTareasdeControl(Planamodificar.getTratamientos());

                            //persistir cambio del plan
                            case 2 -> EliminarTareas(Planamodificar.getTratamientos());

                            //persistir cambio del plan
                            case 3 ->
                                    //crearnuevatarea para la lista así peristimos como base
                                    CrearTareaNueva(Planamodificar.getTratamientos());
                            //persistir la lista de tareas
                            case 4 -> {
                                int diasenfermedad = 0;
                                System.out.println("Ingrese el Cantidad estimada de Dias para la Recuperación de la Enfermedad: ");
                                diasenfermedad = scanner.nextInt();
                                Planamodificar.setTiempo(diasenfermedad);
                            }
                            case 5 -> salir2 = true;
                            default -> {
                                System.out.println("Opción no valida");
                            }
                        }
                    }
                    AsignarPlan(asignar, Planamodificar);
                    planes.add(Planamodificar);
                    guardar.serializarDatos(planes, fileTareas);
                    break;
                case 2:
                    //pacientes con plan de control vigente
                    int ano, mes, dia;
                    for (int i = 0; i < pacientesAsignados.size(); i++) {
                        System.out.println("Opción " + i + " " + pacientesAsignados.get(i));
                    }
                    System.out.println("Ingrese una Opcion");
                    opcion=scanner.nextInt();

                    if(!Objects.equals(pacientesAsignados.get(opcion).getPrincipiodelTratamiento(), LocalDate.now())){
                        System.out.println("Fechas Disponibles "+pacientesAsignados.get(opcion).getPrincipiodelTratamiento()+" hasta"+ LocalDate.now().plusDays(-1));
                        System.out.println("Ingrese Year: ");
                        ano=scanner.nextInt();
                        System.out.println("Ingrese Mes: ");
                        mes=scanner.nextInt();
                        System.out.println("Ingrese Dia: ");
                        dia=scanner.nextInt();

                        System.out.println(pacientesAsignados.get(opcion).BuscarRegistro(LocalDate.of(ano, mes, dia)));
                    }
                    else {
                        System.out.println("No tiene Registros Aun");
                    }

                    break;
                case 3:
                    //dardealta antes de tiempo o modificar fecha
                    for (int i = 0; i < pacientesAsignados.size(); i++) {
                        if (pacientesAsignados.get(i).getPlanDeControl()!=null){
                            System.out.println("Opción " + i + " " + pacientesAsignados.get(i));
                        }
                    }
                    System.out.println("Ingrese una Opción");
                    scanner.nextLine();
                    opcion=scanner.nextInt();
                    FinalizarPlan(pacientesAsignados.get(opcion));
                    pacientesAsignados.remove(opcion);
                    break;
                case 4:
                    salir=true;
                    break;
                default:
                    System.out.println("Opción no valida");
                    break;
            }
        }
    }

    //modifica el plan de control
    @Override
    public void agregarTareasdeControl(ArrayList<TareasDeControl> tareas ) {

        String descripcionNueva;
        int opcion;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Elegir Tratamiento");
        for (int i=0; i<tareas.size(); i++){
            System.out.println("Opcion "+i+" "+tareas.get(i).getDescripcion());
        }
        System.out.print("Opción: ");
        opcion = scanner.nextInt(); //levanta del archivo tareas base

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


        System.out.println("Descripción de la tarea: ");
        descripcionNueva = scanner.nextLine();
        System.out.println("Elegir tipo de tarea: \n" +
                "1 - Dato numérico. \n" +
                "2 - Dato Textual. \n" +
                "3 - Dato si/no\n"
        );
        System.out.println("Opcion: ");
        opcion = scanner.nextInt();

        switch (opcion) {//solo agrega en el paciente no en el plan base
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
            default -> System.out.println("Opcion no Valida");
        }
        //no se como pero hay que usar alguna ecepcion aca seguro
    }

    @Override
    public String toString() {
        return "Medico: "+this.getUsers()+"{" +
                "especializacion='" + especializacion + '\'' +
                ", pacientesAsignados=" + pacientesAsignados +
                '}';
    }
}

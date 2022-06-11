package com.company;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import static java.time.temporal.ChronoUnit.DAYS;

public class Medico extends Usuarios implements AdministraciondeTareasdeControl, Menus {

    private String especializacion;
    private ArrayList<Paciente> pacientesAsignados=new ArrayList<>();

    public Medico(String password, String user, String especializacion) {
        super(password, user);
        this.especializacion = especializacion;
    }

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

    @Override
    public void Menu() throws NullPointerException{
        boolean salir=false;
        int opcion=0;
        Scanner scanner=new Scanner(System.in);
        while (!salir){

            System.out.println("Opcion 1: Asignacion de Planes de Control");
            System.out.println("Opcion 2: Control de los registros de los Pacientes");
            System.out.println("Opcion 3: Finalizacion de Planes de Control");
            System.out.println("Opcion 4: Salir");

            System.out.printf("Ingrese una Opción: ");
            opcion=scanner.nextInt();

            switch (opcion){
                case 1:
                    //pacientes sin plan de control
                    //crear plan de control personalizado o uno ya echo???
                    break;
                case 2:
                    //pacientes con plan de control vigente
                    int ano, mes, dia;
                    for (int i = 0; i < pacientesAsignados.size(); i++) {
                        System.out.println("Opcion " + i + " " + pacientesAsignados.get(i));
                    }
                    System.out.println("Ingrese una Opcion");
                    opcion=scanner.nextInt();
                    System.out.println("Fechas Disponibles "+pacientesAsignados.get(opcion).getPrincipiodelTratamiento()+" hasta"+ LocalDate.now().plusDays(-1));
                    System.out.println("Ingrese Year: ");
                    ano=scanner.nextInt();
                    System.out.println("Ingrese Mes: ");
                    mes=scanner.nextInt();
                    System.out.println("Ingrese Dia: ");
                    dia=scanner.nextInt();

                    System.out.println(pacientesAsignados.get(opcion).BuscarRegistro(LocalDate.of(ano, mes, dia)));
                    break;
                case 3:
                    //dardealta antes de tiempo o modificar fecha
                    for (int i = 0; i < pacientesAsignados.size(); i++) {
                        System.out.println("Opcion " + i + " " + pacientesAsignados.get(i));
                    }
                    System.out.println("Ingrese una Opcion");
                    opcion=scanner.nextInt();
                    FinalizarPlan(pacientesAsignados.get(opcion));
                case 4:
                    salir=true;
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        }
    }

    //modifica el plan de control
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
    //agregar a un arraylist con las tareas de un plan y persistir pero en el paciente
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

    @Override
    public String toString() {
        return "Medico: "+this.getUsers()+"{" +
                "especializacion='" + especializacion + '\'' +
                ", pacientesAsignados=" + pacientesAsignados +
                '}';
    }
}

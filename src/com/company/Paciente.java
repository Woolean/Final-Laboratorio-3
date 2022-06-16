package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Paciente extends Usuarios implements Menus, Serializable {

    private String Enfermedad;
    private String Sintomas;
    private boolean altamedica=false;
    private PlanDeControl planDeControl = null; //se lo inicializa en null para verificar si le asignaron o no un plan el medico
    private LocalDate FindelTratamiento;
    private LocalDate PrincipiodelTratamiento;
    private ArrayList<RegistroDiario> historial = new ArrayList<>();

    public Paciente(String password, String user, String enfermedad, String sintomas) {
        super(password, user);
        Enfermedad = enfermedad;
        Sintomas = sintomas;
    }

    public Paciente() {
    }

    public String getEnfermedad() {
        return Enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        Enfermedad = enfermedad;
    }

    public String getSintomas() {
        return Sintomas;
    }

    public void setSintomas(String sintomas) {
        Sintomas = sintomas;
    }

    public LocalDate getFindelTratamiento() {
        return FindelTratamiento;
    }

    public void setFindelTratamiento(LocalDate findelTratamiento) {
        FindelTratamiento = findelTratamiento;
    }

    public LocalDate getPrincipiodelTratamiento() {
        return PrincipiodelTratamiento;
    }

    public boolean isAltamedica() {
        return altamedica;
    }

    public void setAltamedica(boolean altamedica) {
        this.altamedica = altamedica;
    }

    public void setPrincipiodelTratamiento(LocalDate principiodelTratamiento) {
        PrincipiodelTratamiento = principiodelTratamiento;
    }

    public ArrayList<RegistroDiario> getHistorial() {
        return historial;
    }

    public void setHistorial(ArrayList<RegistroDiario> historial) {
        this.historial = historial;
    }

    public PlanDeControl getPlanDeControl() {
        return planDeControl;
    }

    public void setPlanDeControl(PlanDeControl planDeControl) {
        this.planDeControl = planDeControl;
    }

    public String BuscarRegistro(LocalDate fechadelregistro) {
        for (RegistroDiario e : this.historial) {
            if (e.verificarfecha(fechadelregistro)) {
                return e.getInformacion();
            }
        }
        return "No hay actividad de ese dia por lo que no se completo nada";
    }

    public void actualizarArchivo(){

        //Cosas para los archivos
        File filePacientes = new File("files/Pacientes.json");
        SerializadorPacientes ser1 = new SerializadorPacientes();

        ArrayList<Paciente> listaNueva = new ArrayList<>();

        try {
            listaNueva = ser1.Deserializar(filePacientes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (Paciente paciente : listaNueva) {
            if (Objects.equals(getUsers(), paciente.getUsers())) {
                paciente.setPlanDeControl(this.planDeControl);
                paciente.setHistorial(this.historial);
                //agarego esto porque si modifican esto no se va a guardar
                paciente.setFindelTratamiento(this.FindelTratamiento);
                paciente.setPrincipiodelTratamiento(this.PrincipiodelTratamiento);
            }
        }

        try {
            ser1.Serializar(listaNueva, filePacientes);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //interface menu

    @Override
    public void Menu() throws NullPointerException {
        boolean seguir = false;
        int opcion;
        Scanner scanner = new Scanner(System.in);
        while (!seguir) {
            //excepcion si es null
            System.out.println("Elegir Tratamiento");
            for (int i = 0; i < planDeControl.getTratamientos().size(); i++) {
                System.out.println("Opción " + i + " " + planDeControl.getTratamientos().get(i).getDescripcion());
            }

            System.out.println("Opción " + planDeControl.getTratamientos().size() + ": Cancelar.");
            System.out.print("Ingrese Opción: ");
            opcion = scanner.nextInt();

            if (opcion < planDeControl.getTratamientos().size()) {
                if (planDeControl.getTratamientos().get(opcion) instanceof DatoNumerico) {
                    double dato;
                    System.out.println("Ingresa un valor Numérico");
                    System.out.println(planDeControl.getTratamientos().get(opcion) + ": ");
                    dato = scanner.nextInt();
                    ((DatoNumerico) planDeControl.getTratamientos().get(opcion)).setDatoNumerico(dato);
                } else if (planDeControl.getTratamientos().get(opcion) instanceof DatoBoolean) {
                    int dato;
                    System.out.println("Cambiar estado a 'realizado' escribiendo 1, cualquier otra tecla para Anular");
                    System.out.println(planDeControl.getTratamientos().get(opcion) + ": ");
                    dato = scanner.nextInt();
                    if (dato == 1) {
                        ((DatoBoolean) planDeControl.getTratamientos().get(opcion)).setDatoBoolean(true);
                    } else {
                        ((DatoBoolean) planDeControl.getTratamientos().get(opcion)).setDatoBoolean(false);
                    }
                } else if (planDeControl.getTratamientos().get(opcion) instanceof DatoTextual) {
                    String dato;
                    System.out.println("Ingresa un Texto");
                    System.out.println(planDeControl.getTratamientos().get(opcion) + ": ");
                    dato = scanner.nextLine();
                    ((DatoTextual) planDeControl.getTratamientos().get(opcion)).setDatoTextual(dato);
                }
            } else {
                System.out.println("Saliendo...");
            }

            actualizarArchivo();
            System.out.println("Quiere Salir del Sistema s=SI n=NO");
            scanner.nextLine();
            String si = scanner.nextLine();
            if (si.equalsIgnoreCase("s")) {
                seguir = true;
            }
        }


    }

    @Override
    public String toString() {
        return "Paciente: " + this.getUsers() + " - " +
                "Enfermedad: " + Enfermedad;
    }
}

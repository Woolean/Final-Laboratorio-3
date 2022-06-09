package com.company;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Paciente extends Usuarios implements Menus {

    private String Enfermedad;
    private String Sintomas;
    private PlanDeControl planDeControl=null; //se lo inicializa en null para verificar si le asignaron o no un plan el medico
    private LocalDate FindelTratamiento;
    private LocalDate PrincipiodelTratamiento;
    private ArrayList<RegistroDiario> historial;

    public Paciente(String password, String user, String enfermedad, String sintomas) {
        super(password, user);
        Enfermedad = enfermedad;
        Sintomas = sintomas;
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

    public String BuscarRegistro(LocalDate fechadelregistro){
        for (RegistroDiario e : this.historial){
            if (e.verificarfecha(fechadelregistro)){
                return e.RegistrodeTareas(this.planDeControl.getTratamientos());
            }
        }
        return null;
    }





    //interface menu

    @Override
    public void Menu() {
        boolean seguir=false;
        int opcion;
        Scanner scanner = new Scanner(System.in);

        while (!seguir) {

            System.out.println("Elegir Tratamiento");
            for (int i = 0; i < planDeControl.getTratamientos().size(); i++) {
                System.out.println("Opcion " + i + " " + planDeControl.getTratamientos().get(i).getDescripcion());
            }
            System.out.print("Ingrese Opción: ");
            opcion = scanner.nextInt();

            if (planDeControl.getTratamientos().get(opcion) instanceof DatoNumerico) {
                double dato;
                System.out.println("Ingresa un valor Numerico");
                System.out.println(planDeControl.getTratamientos().get(opcion) + ": ");
                dato = scanner.nextInt();
                ((DatoNumerico) planDeControl.getTratamientos().get(opcion)).setDatoNumerico(dato);
            } else if (planDeControl.getTratamientos().get(opcion) instanceof DatoBoolean) {
                double dato;
                System.out.println("Ingresa un valor Numerico");
                System.out.println(planDeControl.getTratamientos().get(opcion) + ": ");
                dato = scanner.nextInt();
                ((DatoBoolean) planDeControl.getTratamientos().get(opcion)).setDatoBoolean(true);
            } else if (planDeControl.getTratamientos().get(opcion) instanceof DatoTextual) {
                String dato;
                System.out.println("Ingresa un Texto");
                System.out.println(planDeControl.getTratamientos().get(opcion) + ": ");
                dato = scanner.nextLine();
                ((DatoTextual) planDeControl.getTratamientos().get(opcion)).setDatoTextual(dato);
            }

            System.out.println("Quiere Salir del Sistema s=SI n=NO");
            String si= scanner.nextLine();
            if (si.equalsIgnoreCase("s")){
                seguir=true;
            }
        }


    }
}

package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TareasDeControl {

    private String descripcion;
            //estos datos tienen que estar dentro de los metodos, porque no son necesarios afuera
           /* private String descripcionNueva;
    private int opcion, opcionPersonalizada;
    private Scanner nc = new Scanner(System.in);
    private Scanner nc1 = new Scanner(System.in);
    private Scanner ncdescripcion = new Scanner(System.in);*/

    public TareasDeControl(String descripcion) {
        this.descripcion = descripcion;
    }
    public TareasDeControl() {
        this.descripcion = null;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /*public TareasDeControl CrearTarea() {

        System.out.println("Elegir Tratamiento: \n" +
                "1 - Tomar Temperatura. \n" +
                "2 - Tomar Presión. \n" +
                "3 - Tomar Agua. \n" +
                "4 - Consumir medicamentos.\n" +
                "5 - Aplicar tópico. \n" +
                "6 - Personalizar.\n"
        );

        System.out.print("Opción: ");
        opcion = nc.nextInt();

        switch (opcion) {
            case 1 -> {
                DatoNumerico dato = new DatoNumerico("Temperatura", 0);
                return dato;
            }
            case 2 -> {
                DatoNumerico dato = new DatoNumerico("Presión.", 0);
                return dato;
            }
            case 3 -> {
                DatoBoolean dato = new DatoBoolean("Tomar agua.", false);
                return dato;
            }
            case 4 -> {
                DatoBoolean dato = new DatoBoolean("Consumir medicamentos.", false);
                return dato;
            }
            case 5 -> {
                DatoBoolean dato = new DatoBoolean("Aplicar tópico", false);
                return dato;
            }
            case 6 -> {
                System.out.println("Descripción de la tarea: ");
                descripcionNueva = ncdescripcion.nextLine();
                System.out.println("Elegir tipo de tarea: \n" +
                        "1 - Dato numérico. \n" +
                        "2 - Dato Textual. \n" +
                        "3 - Dato si/no \n"
                );
                opcionPersonalizada = nc1.nextInt();
                switch (opcionPersonalizada) {
                    case 1 -> {
                        DatoNumerico dato = new DatoNumerico(descripcionNueva, 0);
                        return dato;
                    }
                    case 2 -> {
                        DatoTextual dato = new DatoTextual(descripcionNueva, null);
                        return dato;
                    }
                    case 3 -> {
                        DatoBoolean dato = new DatoBoolean(descripcionNueva, false);
                        return dato;
                    }
                }
            }
            default -> {
                System.out.println("Error, el número ingresado no corresponde a ninguna opción.");
            }
        }

        return null;

    }
    //separarlasdoscosasagregarunatareayaexistente o crearunanueva porque si no queda una funcion muy larga y separando
    //va a ser mas especifica
    /*public TareasDeControl AgregarTarea(ArrayList<TareasDeControl> tareas) {

         String descripcionNueva;
         int opcion;
         Scanner scanner = new Scanner(System.in);

        System.out.println("Elegir Tratamiento");
        for (int i=0; i<tareas.size(); i++){
            System.out.println("Opcion "+i+" "+tareas.get(i).descripcion);
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


    public TareasDeControl CrearTarea() {

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
    }*/

    /*//paraadminymedicolomismo
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
    }*/

}


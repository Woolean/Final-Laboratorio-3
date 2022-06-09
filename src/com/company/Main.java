package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //persistencia
        ArrayList<Paciente> Pacientes=new ArrayList<>();
        ArrayList<Medico> Medicos=new ArrayList<>();
        ArrayList<Administrador> Administradores=new ArrayList<>();
        ArrayList<TareasDeControl> Tareas=new ArrayList<>();
        ArrayList<Enfermedades> Enfermedades=new ArrayList<>();
        ArrayList<PlanDeControl> PlanesdeControl=new ArrayList<>();

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
            System.out.println("Ingrese Contrasena: ");
            contrasena=scanner1.nextLine();
            UserenSesion=hospital.iniciarSesion(nombre, contrasena);
            if (UserenSesion!=null){
                Seguir=true;
            }
        }

        //menubase o menu en cada clase
        /*boolean salir=false;
        int i=0;
        Scanner scanner=new Scanner(System.in);
        /*while (!salir){

            System.out.println("Opcion 1: Consultar Clientes");
            System.out.println("Opcion 2: Generar nuevo Alquiler");
            System.out.println("Opcion 3: Consultar Alquileres Vigentes");
            System.out.println("Opcion 4: Alquileres por Devolver Hoy");
            System.out.println("Opcion 5: Busqueda de Info de una Pelicula");
            System.out.println("Opcion 6: Busqueda por Genero");
            System.out.println("Opcion 7: Lo mas Alquilado");
            System.out.println("Opcion 8: Devolver una Pelicula");
            System.out.println("Opcion 9: Salir");
            System.out.printf("Ingrese una Opción: ");
            i=scanner.nextInt();

            switch (i){
                case 1:
                    videoclubopen.ConsultarClientes();
                    break;
                case 2:
                    System.out.println("\nGENERANDO ALQUILERES");

                    String nombre;
                    String peli;
                    int opcionpeli;
                    System.out.println("Ingrese la pelicula a Alquilar: ");
                    for (int j=0; j<peliculas.size(); j++){
                        System.out.println("Opcion "+j+" "+peliculas.get(j).getTitulo());
                    }
                    opcionpeli=scanner.nextInt();
                    peli=peliculas.get(opcionpeli).getTitulo();
                    System.out.println("Ingrese Nombre de Cliente: ");
                    nombre=scanner.next();

                    System.out.println(videoclubopen.Alquiler(nombre, peli));

                    break;
                case 3:
                    System.out.println("\nALQUILERES VIGENTES");
                    videoclubopen.ConsultarAlquileresVigentes();
                    break;
                case 4:
                    System.out.println("\nALQUILERES POR DEVOLVER HOY");
                    videoclubopen.ConsultarAlquileresDevolucion();
                    break;
                case 5:
                    System.out.println("\nBUSQUEDA INFO POR TITULO");

                    int opcionpeli2;
                    String peli2;
                    System.out.println("Ingrese la pelicula a Buscar: ");
                    for (int j=0; j<peliculas.size(); j++){
                        System.out.println("Opcion "+j+" "+peliculas.get(j).getTitulo());
                    }
                    opcionpeli2=scanner.nextInt();
                    peli2=peliculas.get(opcionpeli2).getTitulo();
                    System.out.println(videoclubopen.BuscarpeliculaInfo(peli2));
                    break;
                case 6:
                    String generoabuscar;
                    System.out.println("\nGENEROS: ");
                    System.out.println(Generos.AV);
                    System.out.println(Generos.AC);
                    System.out.println(Generos.H);
                    System.out.println(Generos.C);
                    System.out.println(Generos.DO);
                    System.out.println(Generos.DR);
                    System.out.println("Ingrese el Genero a Buscar: ");
                    generoabuscar=scanner.next();
                    String[] peliculasdelgeneroelegido=videoclubopen.BuscarpeliculaGenero(generoabuscar);
                    for (String e: peliculasdelgeneroelegido){
                        if (e!=null){
                            System.out.println(e);
                        }
                    }

                    break;
                case 7:
                    System.out.println("LOS MAS ALQUILADOS");
                    String[] peliculastop=videoclubopen.Ordenacionpormasalquiladas();
                    for (String e: peliculastop){
                        if (e!=null){
                            System.out.println(e);
                        }
                    }

                    break;
                case 8:
                    System.out.println("\nDevolucion de pelicula");
                    int iddelafactura;
                    videoclubopen.ConsultarAlquileresVigentes();
                    System.out.println("Ingrese el Id de la Factura para la Devolucion de la Pelicula: ");
                    iddelafactura=scanner.nextInt();
                    videoclubopen.DevolverFilm(iddelafactura);
                    break;
                case 9:
                    System.out.println("Saliendo......");
                    salir=true;
                    break;
                default:
                    System.out.println("Opcion invalida");
                    break;
            }
        }*/



    }
}

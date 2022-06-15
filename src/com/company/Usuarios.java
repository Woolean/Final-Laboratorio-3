package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Usuarios implements Serializable {
    private static int idsuma;
    private String password;
    private String user;
    //sacar el id

    public Usuarios(String password, String user) {
        try {
            this.password = password;
            this.user = user;
            verificarUsers();
        }catch (Exception e){
            System.out.printf("Ese usuario ya Existe");
        }
    }

    public Usuarios() {
    }

    public String getPasswords() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsers() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void verificarUsers() throws Exception{
        //Cosas para los archivos
        File filePacientes = new File("files/Pacientes.json");
        File fileMedicos = new File("files/Medicos.json");
        File fileAdmins = new File("files/Administradores.json");

        SerializadorPacientes ser1 = new SerializadorPacientes();
        SerializadorMedicos ser2 = new SerializadorMedicos();
        SerializadorAdministrador ser3 = new SerializadorAdministrador();

        ArrayList<Paciente> Pacientes= new ArrayList<>();
        ArrayList<Medico> Medicos= new ArrayList<>();
        ArrayList<Administrador> Administradores= new ArrayList<>();
        ArrayList<Usuarios> users=new ArrayList<>();


        try {
            Pacientes= ser1.Deserializar(filePacientes);
            Medicos= ser2.Deserializar(fileMedicos);
            Administradores= ser3.Deserializar(fileAdmins);
            users.addAll(Pacientes);
            users.addAll(Medicos);
            users.addAll(Administradores);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        for (Usuarios usuarios : users) {
            if (this.equals(usuarios)){
                throw new Exception();
                //exception de que ya existe ese usuario
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuarios usuarios = (Usuarios) o;
        return Objects.equals(user, usuarios.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user);
    }
}


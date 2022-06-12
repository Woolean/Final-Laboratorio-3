package com.company;

public class Usuarios {
    private String password;
    private String user;
    private int id;
    private static int idsuma;

    public Usuarios(String password, String user) {
        this.password = password;
        this.user = user;
        idsuma=idsuma+1;
        this.id = idsuma;
    }

    public Usuarios(){
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}


package com.company;

import java.io.Serializable;

public class Usuarios implements Serializable {
    private static int idsuma;
    private String password;
    private String user;
    private int id;

    public Usuarios(String password, String user) {
        this.password = password;
        this.user = user;
        idsuma = idsuma + 1;
        this.id = idsuma;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}


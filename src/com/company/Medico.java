package com.company;

import java.util.ArrayList;

public class Medico extends Usuarios {

    private String especializacion;
    private ArrayList<Paciente> pacientesAsignados;

    public Medico(String password, String user) {
        super(password, user);
    }


}

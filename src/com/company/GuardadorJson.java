package com.company;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class GuardadorJson <T>{

    GsonBuilder gsonBuilder;
    Gson gson;

    public GuardadorJson(GsonBuilder gsonBuilder, Gson gson) {
        this.gsonBuilder = gsonBuilder;
        this.gson = gson;
    }

    public void serializarDatos(ArrayList Lista, File file) {
        ///Guardar en Json los datos de las listas
        try {
            Type listType = new TypeToken<T>(){}.getType();
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            gson.toJson(Lista, listType, bufferedWriter);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

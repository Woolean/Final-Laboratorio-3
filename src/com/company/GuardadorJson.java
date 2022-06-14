package com.company;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class GuardadorJson {

    GsonBuilder gsonBuilder;
    Gson gson;

    public GuardadorJson(GsonBuilder gsonBuilder, Gson gson) {
        this.gsonBuilder = gsonBuilder;
        this.gson = gson;
    }

    public void serializarDatos(ArrayList Lista, File file) {
        ///Guardar en Json los datos de las listas
        try {
            FileOutputStream jsonFile = new FileOutputStream(file);
            OutputStreamWriter miOutWriter = new OutputStreamWriter(jsonFile);
            String json = gson.toJson(Lista);
            miOutWriter.append(json);
            miOutWriter.close();
            jsonFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

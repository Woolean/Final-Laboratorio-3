package com.company;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;

public class SerializadorMedicos {
    public void Serializar(ArrayList<Medico> x, File archivo) throws IOException {

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(TareasDeControl.class, new AbstractTareasdeControl());
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
        Gson gson = gsonBuilder.setPrettyPrinting().create();


        //Serializo
        Type listType = new TypeToken<ArrayList<Medico>>(){}.getType();
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(archivo));
        gson.toJson(x, listType, bufferedWriter);
        bufferedWriter.close();
    }

    public ArrayList<Medico> Deserializar(File archivo) throws FileNotFoundException {

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
        gsonBuilder.registerTypeAdapter(TareasDeControl.class, new AbstractTareasdeControl());
        Gson gson = gsonBuilder.setPrettyPrinting().create();


        //Deserializo
        Type listType = new TypeToken<ArrayList<Medico>>(){}.getType();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(archivo));
        ArrayList<Medico> result = gson.fromJson(bufferedReader, listType);
        return result;
    }
}

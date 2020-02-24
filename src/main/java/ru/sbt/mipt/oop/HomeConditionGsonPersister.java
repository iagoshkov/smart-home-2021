package ru.sbt.mipt.oop;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HomeConditionGsonPersister implements HomeConditionPersister{

    public SmartHome readHome() throws IOException {
        String filename = "smart-home-1.js";
        Gson gson = new Gson();
        String json = new String(Files.readAllBytes(Paths.get(filename)));
        return gson.fromJson(json, SmartHome.class);
    }
}

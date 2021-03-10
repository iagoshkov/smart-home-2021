package ru.sbt.mipt.oop;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SmartHomeFromJSGetter {
    private final String filename;

    public SmartHomeFromJSGetter(String filename) {
        this.filename = filename;
    }

    public SmartHome get() {
        try {
            Gson gson = new Gson();
            String json = new String(Files.readAllBytes(Paths.get(filename)));
            SmartHome smartHome = gson.fromJson(json, SmartHome.class);
            return smartHome;
        } catch (IOException e) {
            e.printStackTrace();
            return new SmartHome();
        }
    }
}

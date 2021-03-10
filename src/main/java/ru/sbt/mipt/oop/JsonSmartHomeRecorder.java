package ru.sbt.mipt.oop;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonSmartHomeRecorder implements SmartHomeRecorder {

    @Override
    public SmartHome getSmartHome(String path) throws IOException {
        Gson gson = new Gson();
        String json = new String(Files.readAllBytes(Paths.get(path)));

        return gson.fromJson(json, SmartHome.class);
    }

    @Override
    public void saveSmartHome(SmartHome smartHome, String path) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(smartHome);
        System.out.println(jsonString);
        Path fullPath = Paths.get(path);
        try (BufferedWriter writer = Files.newBufferedWriter(fullPath)) {
            writer.write(jsonString);
        }
    }
}
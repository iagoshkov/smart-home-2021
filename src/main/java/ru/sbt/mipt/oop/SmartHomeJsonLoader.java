package ru.sbt.mipt.oop;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SmartHomeJsonLoader implements JsonLoader {
    public static SmartHome readSmartHome(String source) throws IOException {
        Gson gson = new Gson();
        String json = new String(Files.readAllBytes(Paths.get(source)));
        return gson.fromJson(json, SmartHome.class);
    }

    public static void createJSON(SmartHome smartHome, String output) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(smartHome);
        System.out.println(jsonString);

        Path path = Paths.get(output);

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(jsonString);
        }
    }
}

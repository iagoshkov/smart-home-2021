package ru.sbt.mipt.oop;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonHomeWriter implements HomeWriter {

    @Override
    public void homeWriter(SmartHome smartHome, String file) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(smartHome);
        System.out.println(jsonString);
        Path path = Paths.get(file);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

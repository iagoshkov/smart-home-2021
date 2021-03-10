package ru.sbt.mipt.oop.SmartHomeWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.sbt.mipt.oop.SmartHome;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonSmartHomeWriter implements SmartHomeWriter {

    private final Path path;

    public JsonSmartHomeWriter(String filename) {
        this.path = Paths.get(filename);
    }

    public void write(SmartHome smartHome) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(smartHome);

        System.out.println(jsonString);

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(jsonString);
        } catch (IOException e) {
            throw new RuntimeException("Can't write into file by path: " + path);
        }
    }

}

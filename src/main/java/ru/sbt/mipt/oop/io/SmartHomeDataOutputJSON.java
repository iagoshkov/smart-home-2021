package ru.sbt.mipt.oop.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.sbt.mipt.oop.general.SmartHome;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SmartHomeDataOutputJSON implements SmartHomeDataOutput {
    private final String filePath;
    private final Gson gson;

    public SmartHomeDataOutputJSON(String path) {
        filePath = path;
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public void pushData(SmartHome smartHome) throws IOException {
        String jsonString = gson.toJson(smartHome);
        System.out.println(jsonString);
        Path path = Paths.get(filePath);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(jsonString);
        }
    }
}

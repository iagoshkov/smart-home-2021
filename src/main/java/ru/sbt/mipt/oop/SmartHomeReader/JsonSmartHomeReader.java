package ru.sbt.mipt.oop.SmartHomeReader;

import com.google.gson.Gson;
import ru.sbt.mipt.oop.SmartHome;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonSmartHomeReader implements SmartHomeReader {

    private final Path path;

    public JsonSmartHomeReader(String filename) {
        this.path = Paths.get(filename);
    }

    @Override
    public SmartHome read() {
        try {
            Gson gson = new Gson();
            String jsonString = new String(Files.readAllBytes(path));
            return gson.fromJson(jsonString, SmartHome.class);
        } catch (IOException e) {
            throw new RuntimeException("Can't read from file by path: " + path);
        }
    }

}

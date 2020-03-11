package ru.sbt.mipt.oop.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.sbt.mipt.oop.objects.SmartHome;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HomeConditionGsonStorage implements HomeConditionStorage {
    private final String filename;

    public HomeConditionGsonStorage(String filename) {
        this.filename = filename;
    }


    @Override
    public SmartHome readHome() {
        Gson gson = new Gson();
        try {
            String json = new String(Files.readAllBytes(Paths.get(filename)));
            return gson.fromJson(json, SmartHome.class);
        } catch (IOException e) {
             return null;
        }
    }

    @Override
    public void saveHome(SmartHome smartHome) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(smartHome);
        System.out.println(jsonString);
        Path path = Paths.get(filename);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(jsonString);
        } catch (IOException ignored) {
        }
    }
}

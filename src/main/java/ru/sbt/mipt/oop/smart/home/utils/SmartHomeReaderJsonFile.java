package ru.sbt.mipt.oop.smart.home.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.sbt.mipt.oop.smart.home.SmartHome;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SmartHomeReaderJsonFile implements SmartHomeReader {
    private final String filename;

    public SmartHomeReaderJsonFile(String filename) throws IllegalArgumentException {
        if (filename == null) throw new IllegalArgumentException();
        this.filename = filename;
    }

    @Override
    public SmartHome load() {
        Gson gson = createGson();
        String json;

        try {
            json = new String(Files.readAllBytes(Paths.get(filename)));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return gson.fromJson(json, SmartHome.class);
    }

    private Gson createGson() {
        //return (new GsonBuilder()).registerTypeAdapter(SmartDevice.class, new SmartDeviceAdapter()).setPrettyPrinting().create();
        return (new GsonBuilder()).setPrettyPrinting().create();
    }
}

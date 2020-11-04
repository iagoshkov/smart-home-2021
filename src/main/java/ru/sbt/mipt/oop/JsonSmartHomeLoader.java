package ru.sbt.mipt.oop;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonSmartHomeLoader implements ISmartHomeLoader {
    private final String inputFilename;

    public JsonSmartHomeLoader(String inputFilename) {
        this.inputFilename = inputFilename;
    }

    @Override
    public SmartHome load() throws RuntimeException, IOException {
        Gson gson = new Gson();
        String json = new String(Files.readAllBytes(Paths.get(this.inputFilename)));
        SmartHome smartHome = gson.fromJson(json, SmartHome.class);
        return smartHome;
    }
}

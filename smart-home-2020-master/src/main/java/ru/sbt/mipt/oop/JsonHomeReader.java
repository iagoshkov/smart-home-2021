package ru.sbt.mipt.oop;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonHomeReader implements HomeReader {

    @Override
    public SmartHome homeReader(String file) throws IOException {
        Gson gson = new Gson();
        String json = new String(Files.readAllBytes(Paths.get(file)));
        SmartHome smartHome = gson.fromJson(json, SmartHome.class);
        return smartHome;
    }

}

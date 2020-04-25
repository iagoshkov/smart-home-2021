package ru.sbt.mipt.oop.home_readers;

import com.google.gson.Gson;
import ru.sbt.mipt.oop.home_components.SmartHome;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONHomeReader implements HomeReader {

    @Override
    public SmartHome read(String addr) throws IOException {
        Gson gson = new Gson();
        String json = new String(Files.readAllBytes(Paths.get(addr)));
        return gson.fromJson(json, SmartHome.class);
    }
}

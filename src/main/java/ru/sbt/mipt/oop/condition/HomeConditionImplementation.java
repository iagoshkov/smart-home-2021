package ru.sbt.mipt.oop.condition;

import com.google.gson.Gson;
import ru.sbt.mipt.oop.home.SmartHome;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HomeConditionImplementation implements HomeCondition{

    @Override
    public SmartHome smartHomeCondition() throws IOException {
        Gson gson = new Gson();
        String json = new String(Files.readAllBytes(Paths.get("smart-home-1.js")));
        SmartHome smartHome = gson.fromJson(json, SmartHome.class);
        return smartHome;
    }
}

package ru.sbt.mipt.oop;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class HomeBuilder {
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static SmartHome loadFromJson(String file) throws IOException {
        String json = new String(Files.readAllBytes(Paths.get("smart-home-1.js")));
        SmartHome smartHome = gson.fromJson(json, SmartHome.class);
        return smartHome;
    }

    public static void writeToJson(SmartHome smartHome, String file) throws IOException {
        String jsonString = gson.toJson(smartHome);
        System.out.println(jsonString);
        Path path = Paths.get(file);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(jsonString);
        }
    }

    public static void main(String[] args) throws IOException {
        Room kitchen = RoomFactory.getKitchen();
        Room bathroom = RoomFactory.getBathroom();
        Room bedroom = RoomFactory.getBedroom();
        Room hall = RoomFactory.getHall();
        SmartHome smartHome = new SmartHome(Arrays.asList(kitchen, bathroom, bedroom, hall));
        try {
            writeToJson(smartHome, "output.js");
        } catch (IOException e) {
            throw new RuntimeException("Error while writing to json");
        }
    }

}

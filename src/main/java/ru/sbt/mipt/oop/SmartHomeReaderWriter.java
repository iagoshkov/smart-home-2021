package ru.sbt.mipt.oop;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SmartHomeReaderWriter {

    private static Gson createGson() {
        return new GsonBuilder().registerTypeAdapter(SmartDevice.class, new SmartDeviceAdapter()).setPrettyPrinting().create();
    }

    /**
     * Загрузка умного дома из JSON файла
     * @param fileName - путь к json файлу с описанием умного дома
     * @return Загруженный умный дом
     */
    public static SmartHome loadFromJson(String fileName) throws IOException {
        Gson gson = createGson();
        String json = new String(Files.readAllBytes(Paths.get(fileName)));
        return gson.fromJson(json, SmartHome.class);
    }

    /**
     * Выгрузка умного дома в JSON файл
     * @param smartHome - умный дом
     * @param fileName - путь к json файлу, в который требуется записать умный дом
     */
    public static void saveToJson(SmartHome smartHome, String fileName) throws IOException {
        Gson gson = createGson();
        String jsonString = gson.toJson(smartHome);
        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(jsonString);
        }
    }
}

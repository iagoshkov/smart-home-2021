package ru.sbt.mipt.oop;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SmartHomeJsonReaderWriter implements SmartHomeReaderWriter {
    private final String inputFilename;
    private final String outputFilename;

    public SmartHomeJsonReaderWriter(String inputFilename, String outputFilename) {
        this.inputFilename = inputFilename;
        this.outputFilename = outputFilename;
    }

    @Override
    public SmartHome loadSmartHome() throws RuntimeException, IOException {
        Gson gson = new Gson();
        String json = new String(Files.readAllBytes(Paths.get(this.inputFilename)));
        SmartHome smartHome = gson.fromJson(json, SmartHome.class);
        return smartHome;
    }

    @Override
    public void saveSmartHome(SmartHome smartHome) throws RuntimeException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(smartHome);
        System.out.println(jsonString);
        Path path = Paths.get(outputFilename);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

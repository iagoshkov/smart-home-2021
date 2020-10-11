package ru.sbt.mipt.oop.smart.home.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.sbt.mipt.oop.smart.devices.SmartDevice;
import ru.sbt.mipt.oop.smart.devices.SmartDeviceAdapter;
import ru.sbt.mipt.oop.smart.home.SmartHome;

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
    public SmartHome loadSmartHome() throws IOException {
        Gson gson = createGson();
        String json;

        json = new String(Files.readAllBytes(Paths.get(inputFilename)));

        return gson.fromJson(json, SmartHome.class);
    }

    @Override
    public void saveSmartHome(SmartHome smartHome) throws IOException {
        Gson gson = createGson();
        String jsonString = gson.toJson(smartHome);
        Path path = Paths.get(outputFilename);

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(jsonString);
        }
    }

    private Gson createGson() {
        return (new GsonBuilder()).registerTypeAdapter(SmartDevice.class, new SmartDeviceAdapter()).setPrettyPrinting().create();
        //return (new GsonBuilder()).setPrettyPrinting().create();
    }
}

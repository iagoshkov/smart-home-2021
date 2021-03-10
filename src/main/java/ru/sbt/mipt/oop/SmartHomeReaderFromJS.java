package ru.sbt.mipt.oop;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SmartHomeReaderFromJS implements SmartHomeReaderFromFile {
    @Override
    public SmartHome readSmartHomeFromFile(String filePath) {
        Gson gson = new Gson();
        //SmartHome smartHome
        try {
            Reader reader = Files.newBufferedReader(Paths.get(filePath));
            return gson.fromJson(reader, SmartHome.class);
        }
        catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }
}

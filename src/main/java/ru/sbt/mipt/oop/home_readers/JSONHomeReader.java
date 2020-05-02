package ru.sbt.mipt.oop.home_readers;

import com.google.gson.Gson;
import ru.sbt.mipt.oop.home_components.SmartHome;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONHomeReader implements HomeReader {

    @Override
    public SmartHome read(String addr){
        JSONData tempJSON = new JSONData(addr);
        Gson gson = new Gson();
        return gson.fromJson(tempJSON.getData(), SmartHome.class);
    }
}

package ru.sbt.mipt.oop;

import com.google.gson.Gson;
import ru.sbt.mipt.oop.event.EventProcessingCycle;
import ru.sbt.mipt.oop.objects.SmartHome;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Application {

    public static void main(String... args) throws IOException {
        // считываем состояние дома из файла
        Gson gson = new Gson();
        String json = new String(Files.readAllBytes(Paths.get("smart-home-1.js")));
        SmartHome smartHome = gson.fromJson(json, SmartHome.class);
        // начинаем цикл обработки событий
        EventProcessingCycle eventProcessingCycle = new EventProcessingCycle(smartHome);
        eventProcessingCycle.processEventsCycle();
    }
}

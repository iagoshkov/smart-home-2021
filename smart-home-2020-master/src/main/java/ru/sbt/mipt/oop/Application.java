package ru.sbt.mipt.oop;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class Application {

    public static void main(String... args) throws IOException {
        // считываем состояние дома из файла
        HomeReader reader = new JsonHomeReader();
        SmartHome smartHome = reader.homeReader("smart-home-1.js");
        // начинаем цикл обработки событий
        EventCreator creator = new ImpEventCreator();
        SensorEvent event = creator.getNextEvent();
        ProcessEvent processLight, processDoors;
        processLight = new LightProcessEvent();
        processDoors = new DoorsProcessEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
                processLight.process(smartHome, event);
            }
            if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
                processDoors.process(smartHome, event);
            }
            event = creator.getNextEvent();
        }
    }

}

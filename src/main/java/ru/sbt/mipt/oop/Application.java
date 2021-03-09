package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.readers.JSONSmartHomeReader;
import ru.sbt.mipt.oop.readers.SmartHomeReader;

import java.io.IOException;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class Application {

    public static void main(String... args) throws IOException {
        // считываем состояние дома из файла
        String filename = "smart-home-1.js";
        SmartHomeReader reader = new JSONSmartHomeReader(filename);
        SmartHome smartHome = reader.readSmartHome();
        // начинаем цикл обработки событий
        eventLoop(smartHome);
    }

    private static void eventLoop(SmartHome smartHome) {
        SensorEventCreatorImpl sensorEventCreator = new SensorEventCreatorImpl();
        SensorEvent event = sensorEventCreator.getNextSensorEvent();
        EventProcessor eventProcessor = new EventProcessor(smartHome);

        while (event != null) {
            eventProcessor.processEvent(event);
            event = sensorEventCreator.getNextSensorEvent();
        }
    }
}

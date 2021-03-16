package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.readers.JSONSmartHomeReader;
import ru.sbt.mipt.oop.readers.SmartHomeReader;
import ru.sbt.mipt.oop.sensors.SensorEventCreatorImpl;

public class Application {

    public static void main(String... args) {
        // считываем состояние дома из файла
        String filename = "smart-home-1.js";
        SmartHomeReader reader = new JSONSmartHomeReader(filename);
        SmartHome smartHome = reader.readSmartHome();
        // начинаем цикл обработки событий
        EventLoopProcessor eventLoopProcessor = new EventLoopProcessor(smartHome, new SensorEventCreatorImpl());
        eventLoopProcessor.loopEvents();
    }
}

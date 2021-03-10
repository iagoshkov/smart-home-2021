package ru.sbt.mipt.oop;

import java.io.IOException;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class Application {

    public static void main(String... args) {
        // считываем состояние дома из файла
        SmartHome smartHome = new SmartHomeReaderFromJS().readSmartHomeFromFile("smart-home-1.js");
        // начинаем цикл обработки событий
        SensorEventManager sensorEventManager = new SensorEventManager(smartHome);
        sensorEventManager.SensorEventManagerLoop();
    }
}

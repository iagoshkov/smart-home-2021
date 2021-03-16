package ru.sbt.mipt.oop;

import java.io.IOException;

public class Application {
    public static void main(String... args) throws IOException {
        String source = "smart-home-1.js";
        // считываем состояние дома из файла
        SmartHome smartHome = SmartHomeJsonLoader.readSmartHome(source);
        // начинаем цикл обработки событий
        SensorEvent event = EventHandler.getNextSensorEvent();

        EventHandler.EventLoop(smartHome, event);
    }
}

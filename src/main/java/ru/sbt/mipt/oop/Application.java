package ru.sbt.mipt.oop;

import java.io.IOException;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class Application {

    public static void main(String... args) throws IOException {
        // считываем состояние дома из файла
        try {
            SmartHome smartHome = HomeBuilder.loadFromJson("smart-home-1.js");
            // начинаем цикл обработки событий
            SensorEvent event = SensorEventGenerator.getNextSensorEvent();
            while (event != null) {
                event = processEvent(smartHome, event);
            }

        } catch (IOException e) {
            System.out.println("Error while loading from JSON");
        }
    }

    private static SensorEvent processEvent(SmartHome smartHome, SensorEvent event) {
        System.out.println("Got event: " + event);
        if (event.getType().isLightEvent()) {
            // событие от источника света
            smartHome.processLightEvent(event);
        }
        if (event.getType().isDoorEvent()) {
            // событие от двери
            smartHome.processDoorEvent(event);
        }
        event = SensorEventGenerator.getNextSensorEvent();
        return event;
    }


}

package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.handler.EventHandler;

public class SmartHomeSimulator {

    public static void simulateWork(EventHandler eventHandler) {
        SensorEvent event;
        while (true) {
            event = EventGenerator.getNextSensorEvent();
            if (event == null) break;

            eventHandler.handleEvent(event);
        }
    }

}

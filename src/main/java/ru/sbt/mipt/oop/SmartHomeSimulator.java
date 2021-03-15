package ru.sbt.mipt.oop;

public class SmartHomeSimulator {

    public static void simulateWork(SmartHomeEventHandler smartHomeEventHandler) {
        SensorEvent event;
        while (true) {
            event = EventGenerator.getNextSensorEvent();
            if (event == null) break;

            smartHomeEventHandler.handleEvent(event);
        }
    }

}

package ru.sbt.mipt.oop;

import java.io.IOException;

public class Application {

    public static void main(String... args) throws IOException {
        runApplication(new SmartHomeJsonReader("smart-home-1.js"));
    }

    private static void runApplication(SmartHomeReader reader) throws IOException {
        SmartHome smartHome = reader.read();

        SensorEvent event = getNextSensorEvent();
        while (event != null) {
            smartHome.processEvent(event);
            event = getNextSensorEvent();
        }
    }

    static void sendCommand(SensorCommand command) {
        System.out.println("Pretend we're sending command " + command);
    }

    private static SensorEvent getNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) return null; // null means end of event stream
        SensorEventType sensorEventType = SensorEventType.values()[(int) (4 * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(sensorEventType, objectId);
    }
}

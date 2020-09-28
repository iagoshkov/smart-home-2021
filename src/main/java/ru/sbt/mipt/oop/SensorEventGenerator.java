package ru.sbt.mipt.oop;

import java.util.Random;

public class SensorEventGenerator {
    public static Random random = new Random();

    public static SensorEvent getNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) return null; // null means end of event stream
        SensorEventType sensorEventType = SensorEventType.values()[random.nextInt(4)];
        String objectId = String.valueOf(random.nextInt(10));
        return new SensorEvent(sensorEventType, objectId);
    }
}

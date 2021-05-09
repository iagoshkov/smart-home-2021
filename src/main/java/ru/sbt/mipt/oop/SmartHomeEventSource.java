package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class SmartHomeEventSource {
    public SensorEvent getNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) return null; // null means end of event stream
        SensorEventType sensorEventType = SensorEventType.values()[(int) (4 * Math.random())];
        String objectId = "" + (1 + (int) (10 * Math.random()));
        if(sensorEventType.equals(DOOR_OPEN) || sensorEventType.equals(DOOR_CLOSED)) {
            objectId = "" + (1 + (int) (4 * Math.random()));
        }
        return new SensorEvent(sensorEventType, objectId);
    }
}

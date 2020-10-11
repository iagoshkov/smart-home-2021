package ru.sbt.mipt.oop.events;

public class EventGenerator {
    public SensorEvent getNextSensorEvent() {
        if (Math.random() < 0.05D) return null;

        SensorEventType sensorEventType = SensorEventType.values()[(int)(4.0D * Math.random())];
        int offset = sensorEventType != SensorEventType.LIGHT_ON && sensorEventType != SensorEventType.LIGHT_OFF ? 11 : 1;
        String objectId = "" + ((int)(8.0D * Math.random()) + offset);
        return new SensorEvent(sensorEventType, objectId);
    }
}

package ru.sbt.mipt.oop.events;

public class EventGenerator {
    public SensorEvent getNextSensorEvent() {
        double randomNumber = Math.random();
        if (randomNumber < 0.05D) return null;
        if (randomNumber > 0.90D) return new SensorEvent(SensorEventType.ALARM_DEACTIVATE, null);

        SensorEventType sensorEventType = SensorEventType.values()[(int)(4.0D * randomNumber)];
        int offset = sensorEventType != SensorEventType.LIGHT_ON && sensorEventType != SensorEventType.LIGHT_OFF ? 11 : 1;
        String objectId = "" + ((int)(8.0D * randomNumber) + offset);
        return new SensorEvent(sensorEventType, objectId);
    }
}

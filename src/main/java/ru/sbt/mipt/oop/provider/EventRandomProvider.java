package ru.sbt.mipt.oop.provider;

import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.SensorEventType;

public class EventRandomProvider implements EventProvider {
    @Override
    public SensorEvent getNextSensorEvent() {
        if (Math.random() < 0.05) return null;
        SensorEventType sensorEventType = SensorEventType.values()[(int) (4 * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(sensorEventType, objectId);
    }
}

package ru.sbt.mipt.oop.sensorEvent;

import ru.sbt.mipt.oop.SensorEventType;
import ru.sbt.mipt.oop.objects.SmartHome;

public abstract class SensorEvent {
    private final String objectId;
    protected SensorEventType type;

    public SensorEvent(String objectId) {
        this.objectId = objectId;
    }

    public String getObjectId() {
        return objectId;
    }

    @Override
    public String toString() {
        return "SensorEvent{" +
                "type=" + type +
                ", objectId='" + objectId + '\'' +
                '}';
    }

    public abstract void handleEvent(SmartHome smartHome);
}

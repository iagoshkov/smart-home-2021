package ru.sbt.mipt.oop.event;

public class SensorEvent {
    private final String objectId;
    private SensorEventType type;

    public SensorEventType getType() {
        return type;
    }

    public SensorEvent(SensorEventType sensorEventType, String objectId) {
        this.objectId = objectId;
        this.type = sensorEventType;
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
}

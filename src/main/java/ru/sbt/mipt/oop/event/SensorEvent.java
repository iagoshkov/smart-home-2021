package ru.sbt.mipt.oop.event;

import ru.sbt.mipt.oop.Event;

public record SensorEvent(SensorEventType type, String objectId) implements Event {

    public SensorEventType getType() {
        return type;
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

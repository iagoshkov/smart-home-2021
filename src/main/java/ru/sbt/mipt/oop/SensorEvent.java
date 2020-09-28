package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class SensorEvent {
    private final SensorEventType type;
    private final String objectId;

    public SensorEvent(SensorEventType type, String objectId) {
        this.type = type;
        this.objectId = objectId;
    }

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

    public boolean isLightType() {
        if (type == LIGHT_ON || type == LIGHT_OFF) {
            return true;
        }
        return false;
    }

    public boolean isDoorType() {
        if (type == DOOR_OPEN || type == DOOR_CLOSED) {
            return true;
        }
        return false;
    }
}

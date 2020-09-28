package ru.sbt.mipt.oop;

public enum SensorEventType {
    LIGHT_ON, LIGHT_OFF, DOOR_OPEN, DOOR_CLOSED;

    public boolean isLightEvent() {
        return (this.equals(LIGHT_ON) || this.equals(LIGHT_OFF));
    }

    public boolean isDoorEvent() {
        return (this.equals(DOOR_OPEN) || this.equals(DOOR_CLOSED));
    }
}

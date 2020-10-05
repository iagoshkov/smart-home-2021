package ru.sbt.mipt.oop.events;

public enum SensorEventType {
    LIGHT_ON, LIGHT_OFF, DOOR_OPEN, DOOR_CLOSED, LIGHTS_OFF;

    public EventProcessorType getProcessorType() {
        if  ((this.equals(LIGHT_ON) || this.equals(LIGHT_OFF) || this.equals(LIGHTS_OFF))) {
            return EventProcessorType.LIGHT;
        }
        if  ((this.equals(DOOR_OPEN) || this.equals(DOOR_CLOSED))) {
            return EventProcessorType.DOOR;
        }
        return null;
    }
}

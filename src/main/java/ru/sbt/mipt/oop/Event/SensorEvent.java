package ru.sbt.mipt.oop.Event;

public abstract class SensorEvent {
    private String objectId;
  
    public SensorEvent(String objectId) {
        this.objectId = objectId;
    }

    public String getObjectId() {
        return objectId;
    }
}

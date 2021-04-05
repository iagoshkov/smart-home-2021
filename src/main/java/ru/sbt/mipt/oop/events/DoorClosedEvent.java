package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.*;

public class DoorClosedEvent extends SensorEvent {
    private final String objectId;
    private final EventType eventType = EventType.DOOR_CLOSED;

    public DoorClosedEvent(String objectId) {
        this.objectId = objectId;
    }

    @Override
    public EventType getEventType() {
        return eventType;
    }

    @Override
    public String getObjectId() {
        return objectId;
    }
}

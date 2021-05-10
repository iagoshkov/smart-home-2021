package ru.sbt.mipt.oop.Adapter;
import ru.sbt.mipt.oop.Event.*;
import ru.sbt.mipt.oop.Type.EventType;

public class CCDoorEventCreator implements CCEventCreator {
    private EventType type;

public CCDoorEventCreator(EventType type) {
        this.type = type;
    }

    @Override
    public SensorEvent create(String id) {
        return new DoorEvent(id, type);
    }
    
}

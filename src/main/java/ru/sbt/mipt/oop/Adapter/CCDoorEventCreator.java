package ru.sbt.mipt.oop.Adapter;
import ru.sbt.mipt.oop.Event.*;
import ru.sbt.mipt.oop.Type.DoorEventType;

public class CCDoorEventCreator implements CCEventCreator {
    private DoorEventType type;

public CCDoorEventCreator(DoorEventType type) {
        this.type = type;
    }

    @Override
    public SensorEvent create(String id) {
        return new DoorEvent(id, type);
    }
    
}

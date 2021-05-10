  
package ru.sbt.mipt.oop.Event;
import ru.sbt.mipt.oop.Type.EventType;

public class DoorEvent extends SensorEvent{
    private final EventType doorEventType;

    public DoorEvent(String objectId, EventType doorEventType) {
        super(objectId);
        this.doorEventType = doorEventType; 
    }

    public EventType getDoorEventType() {
        return doorEventType;
    }

    @Override
    public String toString() {
        return "DoorEvent{" + "doorEventType=" + doorEventType + ", objectId=" + super.getObjectId() + "}";
    }
}

  
package ru.sbt.mipt.oop.Event;
import ru.sbt.mipt.oop.Type.DoorEventType;

public class DoorEvent extends SensorEvent{
    private DoorEventType doorEventType;

    public DoorEvent(String objectId, DoorEventType doorEventType) {
        super(objectId);
        this.doorEventType = doorEventType;
    }

    public DoorEventType getDoorEventType() {
        return doorEventType;
    }

    @Override
    public String toString() {
        return "DoorEvent{" + "doorEventType=" + doorEventType + ", objectId=" + super.getObjectId() + "}";
    }
}

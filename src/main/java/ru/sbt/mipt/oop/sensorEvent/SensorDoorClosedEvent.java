package ru.sbt.mipt.oop.sensorEvent;

import ru.sbt.mipt.oop.objects.*;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;


public class SensorDoorClosedEvent extends SensorEvent {
    public SensorDoorClosedEvent(String objectId) {
        super(objectId);
        this.type = DOOR_CLOSED;
    }

    protected void closeDoor(SmartHome smartHome) {
        Location location = smartHome.findDoorByID(getObjectId());
        if (location == null) return;;
        location.getObject().setActive(false);
        System.out.println("Door " + location.getObject().getId() + " in room " + location.getRoom().getName() + " was closed.");
    }

    @Override
    public void handleEvent(SmartHome smartHome) {
        closeDoor(smartHome);
    }
}

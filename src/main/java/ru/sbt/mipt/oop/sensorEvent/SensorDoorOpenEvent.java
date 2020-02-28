package ru.sbt.mipt.oop.sensorEvent;

import ru.sbt.mipt.oop.Door;
import ru.sbt.mipt.oop.Location;
import ru.sbt.mipt.oop.SmartHome;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class SensorDoorOpenEvent extends SensorEvent {
    public SensorDoorOpenEvent(String objectId) {
        super(objectId);
        type = DOOR_OPEN;
    }

    @Override
    public void handleEvent(SmartHome smartHome) {
        Location<Door> location = smartHome.findDoorByID(getObjectId());
        location.getObject().setOpen(true);
        System.out.println("Door " + location.getObject().getId() + " in room " + location.getRoom().getName() + " was opened.");
    }
}

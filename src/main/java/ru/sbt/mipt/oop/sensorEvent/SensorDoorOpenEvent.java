package ru.sbt.mipt.oop.sensorEvent;

import ru.sbt.mipt.oop.objects.Door;
import ru.sbt.mipt.oop.objects.Location;
import ru.sbt.mipt.oop.objects.SmartHome;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class SensorDoorOpenEvent extends SensorEvent {
    public SensorDoorOpenEvent(String objectId) {
        super(objectId);
        type = DOOR_OPEN;
    }

    @Override
    public void handleEvent(SmartHome smartHome) {
        Location location = smartHome.findDoorByID(getObjectId());
        if (location == null) return;;
        location.getObject().setActive(true);
        System.out.println("Door " + location.getObject().getId() + " in room " + location.getRoom().getName() + " was opened.");
    }
}

package ru.sbt.mipt.oop.sensorEvent;

import ru.sbt.mipt.oop.Light;
import ru.sbt.mipt.oop.Location;
import ru.sbt.mipt.oop.SmartHome;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class SensorLightOnEvent extends SensorEvent {
    public SensorLightOnEvent(String objectId) {
        super(objectId);
        type = LIGHT_ON;
    }

    @Override
    public void handleEvent(SmartHome smartHome) {
        Location<Light> location = smartHome.findLightByID(getObjectId());
        location.getObject().setOn(true);
        System.out.println("Light " + location.getObject().getId() + " in room " + location.getRoom().getName() + " was turned on.");
    }
}

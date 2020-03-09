package ru.sbt.mipt.oop.sensorEvent;

import ru.sbt.mipt.oop.objects.Light;
import ru.sbt.mipt.oop.objects.Location;
import ru.sbt.mipt.oop.objects.SmartHome;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class SensorLightOnEvent extends SensorEvent {
    public SensorLightOnEvent(String objectId) {
        super(objectId);
        type = LIGHT_ON;
    }

    @Override
    public void handleEvent(SmartHome smartHome) {
        Location location = smartHome.findLightByID(getObjectId());
        if (location == null) return;;
        location.getObject().setActive(true);
        System.out.println("Light " + location.getObject().getId() + " in room " + location.getRoom().getName() + " was turned on.");
    }
}

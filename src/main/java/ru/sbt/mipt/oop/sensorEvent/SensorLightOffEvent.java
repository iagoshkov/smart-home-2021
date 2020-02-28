package ru.sbt.mipt.oop.sensorEvent;

import ru.sbt.mipt.oop.objects.Light;
import ru.sbt.mipt.oop.objects.Location;
import ru.sbt.mipt.oop.objects.SmartHome;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;

public class SensorLightOffEvent extends SensorEvent {
    public SensorLightOffEvent(String objectId) {
        super(objectId);
        type = LIGHT_OFF;
    }

    @Override
    public void handleEvent(SmartHome smartHome) {
        Location<Light> location = smartHome.findLightByID(getObjectId());
        if (location == null) return;;
        location.getObject().setOn(false);
        System.out.println("Light " + location.getObject().getId() + " in room " + location.getRoom().getName() + " was turned off.");
    }
}

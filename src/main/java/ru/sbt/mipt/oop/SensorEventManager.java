package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.*;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;

public class SensorEventManager {
    LightEventHandler lightEventHandler;
    DoorEventHandler doorEventHandler;
    SmartHome smartHome;

    public SensorEventManager(SmartHome smartHome) {
        lightEventHandler = new LightEventHandler();
        doorEventHandler = new DoorEventHandler();
        this.smartHome = smartHome;
    }

    public void SensorEventManagerLoop() {
        SensorEvent event = getNextSensorEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
                lightEventHandler.handleEvent(smartHome, event);
            }
            if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
                doorEventHandler.handleEvent(smartHome, event);
            }
            event = getNextSensorEvent();
        }
    }

    private static SensorEvent getNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) return null; // null means end of event stream
        SensorEventType sensorEventType = SensorEventType.values()[(int) (4 * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(sensorEventType, objectId);
    }
}

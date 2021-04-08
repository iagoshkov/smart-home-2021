package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.*;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;

public class SensorEventCreator {
    LightEventProcessor lightEventProcessor;
    DoorEventProcessor doorEventProcessor;
    SmartHome smartHome;

    public SensorEventCreator(SmartHome smartHome) {

        lightEventProcessor = new LightEventProcessor();
        doorEventProcessor = new DoorEventProcessor();
        this.smartHome = smartHome;
    }

    public void SensorEventCreatorLoop() {

        SensorEvent event = getNextSensorEvent();
        while (event != null) {

            System.out.println("Got event: " + event);

            if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF)
                lightEventProcessor.processEvent(smartHome, event);

            if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED)
                doorEventProcessor.processEvent(smartHome, event);

            event = getNextSensorEvent();
        }
    }

    private static SensorEvent getNextSensorEvent() {

        if (Math.random() < 0.05)
            return null;
        SensorEventType sensorEventType = SensorEventType.values()[(int) (4 * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(sensorEventType, objectId);
    }
}

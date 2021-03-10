package ru.sbt.mipt.oop.event;

import ru.sbt.mipt.oop.objects.SmartHome;

import static ru.sbt.mipt.oop.event.SensorEventType.*;
import static ru.sbt.mipt.oop.event.SensorEventType.DOOR_CLOSED;

public class EventProcessingCycle {
    private final SmartHome smartHome;
    public EventProcessingCycle(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    public void processEventsCycle() {
        SensorEvent event = getNextSensorEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
                LightEventProcessing lightEventProcessing = new LightEventProcessing(event, smartHome);
                lightEventProcessing.processEvent();
            }
            if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
                DoorEventProcessing doorEventProcessing = new DoorEventProcessing(event, smartHome);
                doorEventProcessing.processEvent();
            }
            event = getNextSensorEvent();
        }
    }

    private static SensorEvent getNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) { return null; } // null means end of event stream
        SensorEventType sensorEventType = SensorEventType.values()[(int) (4 * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(sensorEventType, objectId);
    }
}

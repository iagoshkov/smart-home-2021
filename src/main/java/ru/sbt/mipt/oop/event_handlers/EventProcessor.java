package ru.sbt.mipt.oop.event_handlers;

import ru.sbt.mipt.oop.home.SmartHome;

public class EventProcessor {

    SmartHome smartHome;

    public EventProcessor(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    public void processEvent(){
        SensorEvent event = getNextSensorEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            GeneralEventHandler generalEventHandler = new GeneralEventHandler(event,smartHome);
            smartHome = generalEventHandler.applyEvent();
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


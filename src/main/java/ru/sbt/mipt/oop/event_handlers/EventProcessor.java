package ru.sbt.mipt.oop.event_handlers;

import ru.sbt.mipt.oop.home.SmartHome;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class EventProcessor implements Iterator<SensorEvent>{

    SmartHome smartHome;

    public EventProcessor(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    public void processEvent(){
        EventGenerator eventGenerator = new EventGenerator();
        SensorEvent event = eventGenerator.next();
        EventSolverImplementation eventSolverImplementation = new EventSolverImplementation();
        while (event != null) {
            System.out.println("Got event: " + event);
            eventSolverImplementation.solveEvent(smartHome, event);
            event = eventGenerator.next();
        }
    }

    private static SensorEvent getNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) return null; // null means end of event stream
        SensorEventType sensorEventType = SensorEventType.values()[(int) (4 * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(sensorEventType, objectId);
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public SensorEvent next() {
        return getNextSensorEvent();
    }
}


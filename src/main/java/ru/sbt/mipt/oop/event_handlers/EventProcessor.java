package ru.sbt.mipt.oop.event_handlers;

import ru.sbt.mipt.oop.home.SmartHome;

public class EventProcessor {

    SmartHome smartHome;
    EventSolverImplementation eventSolverImplementation;
    EventGenerator eventGenerator;
    SensorEvent event;

    public EventProcessor(SmartHome smartHome, EventGenerator eventGenerator, EventSolverImplementation eventSolverImplementation) {
        this.eventGenerator = eventGenerator;
        this.smartHome = smartHome;
        this.eventSolverImplementation = eventSolverImplementation;
        this.event = eventGenerator.makeEvent();
    }

    public void processEvent(){
        while (event != null) {
            System.out.println("Got event: " + event);
            eventSolverImplementation.solveEvent(smartHome, event);
            event = eventGenerator.makeEvent();
        }
    }
}


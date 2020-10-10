package ru.sbt.mipt.oop.event_handlers;

import ru.sbt.mipt.oop.home.SmartHome;

public class EventProcessor {

    SmartHome smartHome;
    EventSolverImplementation eventSolverImplementation = new EventSolverImplementation();
    EventGenerator eventGenerator = new EventGenerator();

    public EventProcessor(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    public void processEvent(){
        SensorEvent event = eventGenerator.makeEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            eventSolverImplementation.solveEvent(smartHome, event);
            event = eventGenerator.makeEvent();
        }
    }
}


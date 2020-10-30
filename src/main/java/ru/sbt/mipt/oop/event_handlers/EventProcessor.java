package ru.sbt.mipt.oop.event_handlers;

import ru.sbt.mipt.oop.home.SmartHome;

public class EventProcessor {

    SmartHome smartHome;
    EventSolverImplementation eventSolverImplementation;
    EventGenerator eventGenerator;
    SensorEvent event;
    EventSolver eventSolverDecorator;

    public EventProcessor(SmartHome smartHome, EventGenerator eventGenerator, EventSolverImplementation eventSolverImplementation, EventSolver eventSolverDecorator) {
        this.eventGenerator = eventGenerator;
        this.smartHome = smartHome;
        this.eventSolverImplementation = eventSolverImplementation;
        this.event = eventGenerator.makeEvent();
        this.eventSolverDecorator = eventSolverDecorator;
    }

    public void processEvent(){
        SensorEvent event = eventGenerator.makeEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            eventSolverDecorator.solveEvent(smartHome, event);
            event = eventGenerator.makeEvent();
        }
    }
}


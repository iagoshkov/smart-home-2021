package ru.sbt.mipt.oop.event_handlers;

import ru.sbt.mipt.oop.home.SmartHome;

public class EventProcessor {

    SmartHome smartHome;
    EventSolverImplementation eventSolverImplementation;
    EventGenerator eventGenerator;
    EventSolverDecorator eventSolverDecorator;


    public EventProcessor(SmartHome smartHome, EventSolverImplementation eventSolverImplementation, EventGenerator eventGenerator) {
        this.eventSolverImplementation = eventSolverImplementation;
        this.eventGenerator = eventGenerator;
        this.smartHome = smartHome;
        this.eventSolverDecorator = new EventSolverDecorator(eventSolverImplementation, new AlarmEventHandler(smartHome), smartHome);
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


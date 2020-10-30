package ru.sbt.mipt.oop.event_handlers;
import ru.sbt.mipt.oop.home.SmartHome;

import java.util.ArrayList;

public class EventSolverImplementation implements EventSolver{
    ArrayList<GeneralEvent> events;
    LightEventHandler lightEventHandler;
    DoorEventHandler doorEventHandler;
    ClosedHallDoorEventHandler closedHallDoorEvent;

    public EventSolverImplementation(ArrayList<GeneralEvent> events, LightEventHandler lightEventHandler, DoorEventHandler doorEventHandler, ClosedHallDoorEventHandler closedHallDoorEventHandler) {
        this.events = events;
        this.lightEventHandler = lightEventHandler;
        this.doorEventHandler = doorEventHandler;
        this.closedHallDoorEvent = closedHallDoorEventHandler;
        events.add(lightEventHandler);
        events.add(doorEventHandler);
        events.add(closedHallDoorEventHandler);
    }

    @Override
    public void solveEvent(SmartHome smartHome, SensorEvent event) {
        for (GeneralEvent eventToHandle : events) {
            eventToHandle.handleEvent(event, smartHome);
        }
    }
}

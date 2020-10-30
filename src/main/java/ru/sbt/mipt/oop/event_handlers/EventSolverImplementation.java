package ru.sbt.mipt.oop.event_handlers;
import ru.sbt.mipt.oop.home.SmartHome;

import java.util.ArrayList;
import java.util.List;

public class EventSolverImplementation implements EventSolver{
    List<GeneralEvent> eventHandlersList;

    public EventSolverImplementation(List<GeneralEvent> events) {
        this.eventHandlersList = events;
    }

    @Override
    public void solveEvent(SmartHome smartHome, SensorEvent event) {
        for (GeneralEvent eventToHandle : eventHandlersList) {
            eventToHandle.handleEvent(event, smartHome);
        }
    }
}

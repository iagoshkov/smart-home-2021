package ru.sbt.mipt.oop.event_handlers;
import ru.sbt.mipt.oop.home.SmartHome;

import java.util.ArrayList;

public class EventSolverImplementation implements EventSolver{
    ArrayList<GeneralEvent> events = new ArrayList<>();
    LightEventHandler lightEventHandler = new LightEventHandler();
    DoorEventHandler doorEventHandler = new DoorEventHandler();

    public EventSolverImplementation() {
        events.add(lightEventHandler);
        events.add(doorEventHandler);
    }

    @Override
    public void solveEvent(SmartHome smartHome, SensorEvent event) {
        for (GeneralEvent eventToHandle : events) {
            eventToHandle.handleEvent(event, smartHome);
        }
    }
}

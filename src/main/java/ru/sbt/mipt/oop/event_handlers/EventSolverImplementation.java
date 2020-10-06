package ru.sbt.mipt.oop.event_handlers;

import ru.sbt.mipt.oop.home.SmartHome;

import static ru.sbt.mipt.oop.event_handlers.SensorEventType.*;
import static ru.sbt.mipt.oop.event_handlers.SensorEventType.DOOR_CLOSED;

public class EventSolverImplementation implements EventSolver{

    @Override
    public void solveEvent(SmartHome smartHome, SensorEvent event) {
        if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
            // событие от источника света
            smartHome.handleLightEvent(event);
        }

        if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
            // событие от двери
            smartHome.handleDoorEvent(event);
        }
    }
}

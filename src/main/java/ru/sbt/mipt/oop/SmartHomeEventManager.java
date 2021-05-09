package ru.sbt.mipt.oop;

import java.util.List;

import static ru.sbt.mipt.oop.SensorEventType.*;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;

public class SmartHomeEventManager implements EventManager {
    private final List<EventHandler> eventHandlers;

    public SmartHomeEventManager(List<EventHandler> eventHandlers) {
        this.eventHandlers = eventHandlers;
    }

    @Override
    public void manageEvent(SensorEvent event) {
        eventHandlers.forEach(eventHandler -> eventHandler.handleEvent(event));
    }
}

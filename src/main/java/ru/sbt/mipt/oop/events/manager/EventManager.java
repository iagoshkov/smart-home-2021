package ru.sbt.mipt.oop.events.manager;

import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.events.handlers.IEventHandler;
import ru.sbt.mipt.oop.sensor.event.SensorEvent;

import java.util.ArrayList;
import java.util.List;

public class EventManager implements IEventManager {
    private SmartHome smartHome;
    private List<IEventHandler> eventHandlers = new ArrayList<>();

    @Override
    public void setSmartHome(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void addHandler(IEventHandler eventHandler) {
        this.eventHandlers.add(eventHandler);
    }

    @Override
    public void processEvent(SensorEvent sensorEvent) {
        for (IEventHandler eventHandler : eventHandlers) {
            eventHandler.processEvent(smartHome, sensorEvent);
        }
    }
}

package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.eventhandler.EventHandler;
import ru.sbt.mipt.oop.provider.EventProvider;

import java.util.List;

public class EventsRegistrar {
    private final EventProvider eventProvider;
    private final List<EventHandler> eventHandlers;

    public EventsRegistrar(EventProvider eventProvider, List<EventHandler> eventHandlers) {
        this.eventProvider = eventProvider;
        this.eventHandlers = eventHandlers;
    }

    public void registerEvents() {
        SensorEvent event = eventProvider.getNextSensorEvent();
        while (event != null) {
            for (EventHandler eventHandler: eventHandlers) {
                eventHandler.handleEvent(event);
            }
            event = eventProvider.getNextSensorEvent();
        }
    }
}

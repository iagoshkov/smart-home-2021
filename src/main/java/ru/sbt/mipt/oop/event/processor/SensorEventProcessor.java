package ru.sbt.mipt.oop.event.processor;

import ru.sbt.mipt.oop.event.Event;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.event.handler.EventHandler;

import java.util.List;

public record SensorEventProcessor(SmartHome smartHome,
                                   List<EventHandler> eventHandlers) implements EventProcessor {
    @Override
    public void processEvent(Event event) {
        System.out.println("Got event: " + event);

        if (event instanceof SensorEvent sensorEvent) {
            for (EventHandler eventHandler : eventHandlers) {
                eventHandler.handleEvent(sensorEvent);
            }
        }
    }
}

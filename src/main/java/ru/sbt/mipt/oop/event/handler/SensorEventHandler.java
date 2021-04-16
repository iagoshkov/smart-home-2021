package ru.sbt.mipt.oop.event.handler;

import ru.sbt.mipt.oop.Event;
import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.event.processor.EventProcessor;

import java.util.List;

public record SensorEventHandler(SmartHome smartHome,
                                 List<EventProcessor> eventProcessors) implements EventHandler {
    public void handleEvent(Event event) {
        System.out.println("Got event: " + event);

        if (event instanceof SensorEvent sensorEvent) {
            for (EventProcessor eventProcessor : eventProcessors) {
                eventProcessor.processEvent(smartHome, sensorEvent);
            }
        }
    }
}

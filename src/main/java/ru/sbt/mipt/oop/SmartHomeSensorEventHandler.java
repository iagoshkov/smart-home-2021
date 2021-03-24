package ru.sbt.mipt.oop;

import java.util.List;

public record SmartHomeSensorEventHandler(SmartHome smartHome,
                                          List<EventProcessor> eventProcessors) implements SmartHomeEventHandler {
    public void handleEvent(Event event) {
        System.out.println("Got event: " + event);

        if (event instanceof SensorEvent sensorEvent) {
            for (EventProcessor eventProcessor : eventProcessors) {
                eventProcessor.processEvent(smartHome, sensorEvent);
            }
        }
    }
}

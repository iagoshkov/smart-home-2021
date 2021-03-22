package ru.sbt.mipt.oop;

import java.util.List;

public record SmartHomeEventHandler(SmartHome smartHome,
                                    List<EventProcessor> eventProcessors) {
    public void handleEvent(SensorEvent event) {
        System.out.println("Got event: " + event);

        for (EventProcessor eventProcessor : eventProcessors) {
            eventProcessor.processEvent(smartHome, event);
        }
    }
}

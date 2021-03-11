package ru.sbt.mipt.oop;

import java.util.List;

public class SmartHomeEventHandler {

    private final SmartHome smartHome;
    private final List<EventProcessor> eventProcessors;

    public SmartHomeEventHandler(SmartHome smartHome, List<EventProcessor> eventProcessors) {
        this.smartHome = smartHome;
        this.eventProcessors = eventProcessors;
    }

    public void handleEvent(SensorEvent event) {
        System.out.println("Got event: " + event);

        for (EventProcessor eventProcessor : eventProcessors) {
            eventProcessor.processEvent(smartHome, event);
        }
    }

}

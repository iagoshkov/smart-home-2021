package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.objects.SmartHome;
import ru.sbt.mipt.oop.sensorEvent.SensorEvent;

public class EventHandler {
    private final SmartHome smartHome;

    public EventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }


    public void handleEvent(SensorEvent event) {
        System.out.println("Got event: " + event);
        event.handleEvent(smartHome);
    }
}

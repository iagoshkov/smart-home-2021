package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.events.EventGenerator;
import ru.sbt.mipt.oop.events.EventProcessor;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.smart.home.SmartHome;

public class MainLoop {
    private final EventGenerator eventGenerator;
    private final EventProcessor eventProcessor;

    public MainLoop(EventProcessor eventProcessor, EventGenerator eventGenerator) throws IllegalArgumentException {
        if (eventProcessor == null || eventGenerator == null) throw new IllegalArgumentException();
        this.eventProcessor = eventProcessor;
        this.eventGenerator = eventGenerator;
    }

    public void run(SmartHome smartHome) {
        while(true) {
            SensorEvent event = eventGenerator.getNextSensorEvent();
            if (event == null) return;

            System.out.println("Got event: " + event);
            eventProcessor.executeEvent(event, smartHome);
        }
    }
}

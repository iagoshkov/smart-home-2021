package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.processor.EventProcessor;

public class SmartHomeSimulator {

    public static void simulateWork(EventProcessor eventProcessor) {
        SensorEvent event;
        while (true) {
            event = EventGenerator.getNextSensorEvent();
            if (event == null) break;

            eventProcessor.processEvent(event);
        }
    }

}

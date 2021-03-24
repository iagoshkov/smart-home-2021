package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.generators.EventGenerator;
import ru.sbt.mipt.oop.events.generators.RandomEventGenerator;
import ru.sbt.mipt.oop.events.processors.AnyEventProcessor;

public class EventLoop {
    private final AnyEventProcessor eventProcessor;
    private final EventGenerator eventGenerator = new RandomEventGenerator();

    public EventLoop(AnyEventProcessor eventProcessor) {
        this.eventProcessor = eventProcessor;
    }

    public void startLoop() {
        SensorEvent event = eventGenerator.generateEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            eventProcessor.processEvent(event);
            event = eventGenerator.generateEvent();
        }
    }
}

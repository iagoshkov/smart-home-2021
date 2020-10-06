package ru.sbt.mipt.oop.events.processors;

import ru.sbt.mipt.oop.elements.SmartHome;
import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.EventGenerator;
import ru.sbt.mipt.oop.events.typedefs.ProcessorType;

import java.util.Map;
import java.util.TreeMap;

public class EventProcessorComposite implements EventProcessor {
    private final EventGenerator eventGenerator;
    private final Map<ProcessorType, EventProcessor> processors;

    public EventProcessorComposite(SmartHome smartHome) {
        eventGenerator = new SensorEventGenerator(smartHome);
        this.processors = new TreeMap<>();
    }

    public void addEventProcessor(ProcessorType type, EventProcessor processor) {
        processors.putIfAbsent(type, processor);
    }

    public EventProcessor getEventProcessor(ProcessorType type) {
        return processors.get(type);
    }

    public Event processEvent(SmartHome smartHome, Event event) {
        if (event == null) { //first iteration
            event = eventGenerator.getNextEvent();
        }
        if (event != null) {
            System.out.println("Got event: " + event);
            Event resultEvent = processors.get(event.getType().getProcessorType()).processEvent(smartHome, event);
            if (event.equals(resultEvent)) {
                resultEvent = eventGenerator.getNextEvent();
            }
            return resultEvent;
        } else {
            return null;
        }
    }

}

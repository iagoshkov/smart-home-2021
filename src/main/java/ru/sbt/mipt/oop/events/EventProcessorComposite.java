package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.SmartHome;

import java.util.Map;
import java.util.TreeMap;

public class EventProcessorComposite {
    private final SensorEventGenerator sensorEventGenerator;
    private final Map<EventProcessorType, EventProcessor> processors;

    public EventProcessorComposite() {
        sensorEventGenerator = new SensorEventGenerator();
        this.processors = new TreeMap<>();
    }

    public void addEventProcessor(EventProcessorType type, EventProcessor processor) {
        processors.putIfAbsent(type, processor);
    }

    public EventProcessor getEventProcessor(EventProcessorType type) {
        return processors.get(type);
    }

    public SensorEvent processEvent(SmartHome smartHome, SensorEvent event) {
        if (event == null) {
            event = sensorEventGenerator.getNextSensorEvent();
        }
        System.out.println("Got event: " + event);
        SensorEvent resultEvent = processors.get(event.getType().getProcessorType()).processEvent(smartHome, event);
        if (resultEvent.equals(event)) {
            resultEvent = sensorEventGenerator.getNextSensorEvent();
        }
        return resultEvent;
    }

}

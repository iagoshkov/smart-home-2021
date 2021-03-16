package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.sensors.SensorEvent;
import ru.sbt.mipt.oop.sensors.SensorEventCreator;

public class EventLoopProcessor {
    private final SmartHome smartHome;
    private final SensorEventCreator sensorEventCreator;

    public EventLoopProcessor(SmartHome smartHome, SensorEventCreator sensorEventCreator) {
        this.smartHome = smartHome;
        this.sensorEventCreator = sensorEventCreator;
    }

    public void loopEvents() {
        SensorEvent event = sensorEventCreator.getNextSensorEvent();
        EventProcessor eventProcessor = new EventProcessor(smartHome);

        while (event != null) {
            eventProcessor.processEvent(event);
            event = sensorEventCreator.getNextSensorEvent();
        }
    }
}

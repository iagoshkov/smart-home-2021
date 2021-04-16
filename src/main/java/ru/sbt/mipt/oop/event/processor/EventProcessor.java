package ru.sbt.mipt.oop.event.processor;

import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;

public interface EventProcessor {
    void processEvent(SmartHome smartHome, SensorEvent event);
}

package ru.sbt.mipt.oop;

import java.util.List;

public interface EventProcessor {
    void processEvent(SmartHome smartHome, SensorEvent event);
}

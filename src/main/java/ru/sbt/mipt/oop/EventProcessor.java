package ru.sbt.mipt.oop;

import java.util.function.Function;

public interface EventProcessor {
    public void processEvent(SmartHome smartHome, SensorEvent event);
}

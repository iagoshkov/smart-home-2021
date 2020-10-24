package ru.sbt.mipt.oop.events.adpter;

import ru.sbt.mipt.oop.events.SensorEventType;

public interface SensorEventFactory {
    SensorEventType getType(String name);
}

package ru.sbt.mipt.oop.provider;

import ru.sbt.mipt.oop.SensorEvent;

public interface EventProvider {
    SensorEvent getNextSensorEvent();
}

package ru.sbt.mipt.oop.provider;

import ru.sbt.mipt.oop.event.SensorEvent;

public interface EventProvider {
    SensorEvent getNextSensorEvent();
}

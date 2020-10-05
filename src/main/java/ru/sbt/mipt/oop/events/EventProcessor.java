package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.SmartHome;

public interface EventProcessor {
    SensorEvent processEvent(SmartHome smartHome, SensorEvent event);
}

package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.SmartHome;

public interface IEventHandler {
    void processEvent(SmartHome smartHome, SensorEvent event);
}

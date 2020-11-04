package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.sensor.event.SensorEvent;
import ru.sbt.mipt.oop.components.SmartHome;

public interface IEventManager {
    void setSmartHome(SmartHome smartHome);
    void addHandler(IEventHandler handler);
    void processEvent(SensorEvent event);
}

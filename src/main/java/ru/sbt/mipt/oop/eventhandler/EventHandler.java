package ru.sbt.mipt.oop.eventhandler;

import ru.sbt.mipt.oop.event.SensorEvent;

public interface EventHandler {
    void handleEvent(SensorEvent event);
}

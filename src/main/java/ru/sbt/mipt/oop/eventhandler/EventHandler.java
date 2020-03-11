package ru.sbt.mipt.oop.eventhandler;

import ru.sbt.mipt.oop.SensorEvent;

public interface EventHandler {
    void handleEvent(SensorEvent event);
}

package ru.sbt.mipt.oop.EventHandler;

import ru.sbt.mipt.oop.SensorEvent;

public interface EventHandler {
    void handleEvent(SensorEvent event);
}

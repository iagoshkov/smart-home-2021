package ru.sbt.mipt.oop.event.handler;

import ru.sbt.mipt.oop.event.SensorEvent;

public interface EventHandler {
    void handleEvent(SensorEvent event);
}

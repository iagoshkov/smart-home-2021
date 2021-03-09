package ru.sbt.mipt.oop;


// interface EventHandler is used to separate logic of event handling
public interface EventHandler {
    void handleEvent(SensorEvent event);
}

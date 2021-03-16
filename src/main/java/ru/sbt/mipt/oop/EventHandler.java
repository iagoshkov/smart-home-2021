package ru.sbt.mipt.oop;

import java.util.List;

public interface EventHandler {
    // can return null
    List<CommandType> handleEvent(SmartHome smartHome, SensorEvent event);
}

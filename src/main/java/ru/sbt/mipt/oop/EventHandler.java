package ru.sbt.mipt.oop;

import java.util.List;

public interface EventHandler {
    List<CommandType> handleEvent(SmartHome smartHome, SensorEvent event);
}

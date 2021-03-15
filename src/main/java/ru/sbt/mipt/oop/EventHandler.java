package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.List;

public interface EventHandler {
    default List<CommandType> handleEvent(SmartHome smartHome, SensorEvent event) {
        return new ArrayList<>();
    }
}

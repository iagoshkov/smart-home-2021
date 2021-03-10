package ru.sbt.mipt.oop;

import java.util.List;

public interface EventProcessor {
    List<CommandType> processEvent(SensorEvent event);
}

package ru.sbt.mipt.oop.event_handlers;

import ru.sbt.mipt.oop.home.SmartHome;

import java.util.List;

public interface EventSolver {
    void solveEvent(SmartHome smartHome, SensorEvent event);
}

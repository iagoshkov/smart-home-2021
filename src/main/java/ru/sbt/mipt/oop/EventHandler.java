package ru.sbt.mipt.oop;

import java.util.function.Function;

public interface EventHandler {
    Action handleEvent(SensorEvent event);
}

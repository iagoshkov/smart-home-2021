package ru.sbt.mipt.oop.Handler;
import ru.sbt.mipt.oop.Event.SensorEvent;

public interface SensorEventHandler {
    void handleEvent(SensorEvent event);
}

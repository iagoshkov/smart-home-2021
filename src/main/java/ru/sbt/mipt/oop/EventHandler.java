package ru.sbt.mipt.oop;

public interface EventHandler {
    CommandType handleEvent(SensorEvent event, Room room, Light light, Door door);
}

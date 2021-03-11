package ru.sbt.mipt.oop;

public interface EventHandler {
    CommandType handleEvent(Room room, Light light, Door door);
}

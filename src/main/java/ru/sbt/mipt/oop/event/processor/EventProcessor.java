package ru.sbt.mipt.oop.event.processor;

import ru.sbt.mipt.oop.event.Event;

public interface EventProcessor {
    void processEvent(Event event);
}

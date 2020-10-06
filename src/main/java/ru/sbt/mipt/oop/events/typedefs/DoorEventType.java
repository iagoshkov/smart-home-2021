package ru.sbt.mipt.oop.events.typedefs;

import ru.sbt.mipt.oop.events.processors.EventProcessorType;

public enum DoorEventType implements EventType {
    DOOR_OPEN, DOOR_CLOSED;

    public ProcessorType getProcessorType() {
        return EventProcessorType.DOOR;
    }
}

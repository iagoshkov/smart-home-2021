package ru.sbt.mipt.oop.events.typedefs;

import ru.sbt.mipt.oop.events.processors.EventProcessorType;

public enum LightEventType implements EventType {
    LIGHT_ON, LIGHT_OFF;

    public ProcessorType getProcessorType() {
        return EventProcessorType.LIGHT;
    }
}

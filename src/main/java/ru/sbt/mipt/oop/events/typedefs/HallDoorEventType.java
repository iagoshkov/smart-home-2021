package ru.sbt.mipt.oop.events.typedefs;

import static ru.sbt.mipt.oop.events.processors.EventProcessorType.HALL_DOOR;

public enum HallDoorEventType implements EventType {
    LIGHTS_ON, LIGHTS_OFF;

    @Override
    public ProcessorType getProcessorType() {
        return HALL_DOOR;
    }
}

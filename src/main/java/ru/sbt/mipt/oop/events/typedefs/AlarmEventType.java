package ru.sbt.mipt.oop.events.typedefs;

import ru.sbt.mipt.oop.events.AlarmEvent;
import ru.sbt.mipt.oop.events.processors.EventProcessorType;

public enum AlarmEventType implements EventType {
    ALARM_ACTIVATE, ALARM_DEACTIVATE, ALARM_WARNING;

    @Override
    public ProcessorType getProcessorType() {
        return EventProcessorType.ALARM;
    }
}

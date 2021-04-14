package ru.sbt.mipt.oop.alarm;

import ru.sbt.mipt.oop.Event;

public record AlarmEvent(AlarmEventType type, String code) implements Event {
    @Override
    public String toString() {
        return "AlarmSystemEvent{" +
                "type=" + type +
                ", code='" + code + '\'' +
                '}';
    }
}

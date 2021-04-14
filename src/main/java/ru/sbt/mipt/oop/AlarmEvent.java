package ru.sbt.mipt.oop;

public record AlarmEvent(AlarmEventType type, String code) implements Event {
    @Override
    public String toString() {
        return "AlarmSystemEvent{" +
                "type=" + type +
                ", code='" + code + '\'' +
                '}';
    }
}

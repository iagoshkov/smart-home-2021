package ru.sbt.mipt.oop;

public record AlarmSystemEvent(AlarmSystemEventType type, String code) implements Event {
    @Override
    public String toString() {
        return "AlarmSystemEvent{" +
                "type=" + type +
                ", code='" + code + '\'' +
                '}';
    }
}

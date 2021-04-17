package ru.sbt.mipt.oop.event;

public class AlarmEvent implements Event {

    private final AlarmEventType type;
    private final String code;

    public AlarmEventType getType() {
        return type;
    }

    public String getCode() {
        return code;
    }

    public AlarmEvent(AlarmEventType type, String code) {
        this.type = type;
        this.code = code;
    }

    @Override
    public String toString() {
        return "AlarmEvent{" +
                "type=" + type +
                ", code='" + code + '\'' +
                '}';
    }

}

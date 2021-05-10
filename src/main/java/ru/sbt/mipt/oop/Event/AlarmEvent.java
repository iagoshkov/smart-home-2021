package ru.sbt.mipt.oop.Event;
import ru.sbt.mipt.oop.Type.EventType;

public class AlarmEvent extends SensorEvent {
    private final EventType alarmEventType;
    private final String code; 

    public AlarmEvent(EventType alarmEventType, String code) {
        super("alarm");
        this.alarmEventType = alarmEventType;
        this.code = code;
    }

    public EventType getAlarmEventType() {
        return alarmEventType;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "AlarmEvent{" + "alarmEventType=" + alarmEventType + ", code='" + code + "\'" + "}";
    }
}

package ru.sbt.mipt.oop.Event;
import ru.sbt.mipt.oop.Type.AlarmEventType;

public class AlarmEvent extends SensorEvent {
    private AlarmEventType alarmEventType;
    private String code;

    public AlarmEvent(AlarmEventType alarmEventType, String code) {
        super("alarm");
        this.alarmEventType = alarmEventType;
        this.code = code;
    }

    public AlarmEventType getAlarmEventType() {
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

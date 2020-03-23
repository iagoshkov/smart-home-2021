package ru.sbt.mipt.oop.event;

public class SensorAlarmEvent extends SensorEvent {
    private final String code;

    public SensorAlarmEvent(SensorEventType sensorEventType, String objectId, String code) {
        super(sensorEventType, objectId);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}

package ru.sbt.mipt.oop.Objects.Alarm;

public class DeactivatedAlarmState implements AlarmState {

    @Override
    public AlarmState activate(String code) {
        return new ActivatedAlarmState(code);
    }

    @Override
    public AlarmState deactivate(String code) {
        return this;
    }

    @Override
    public AlarmState alert() {
        return this;
    }
}

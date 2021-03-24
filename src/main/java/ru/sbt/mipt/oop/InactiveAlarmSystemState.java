package ru.sbt.mipt.oop;

public class InactiveAlarmSystemState extends AlarmSystemState{

    protected InactiveAlarmSystemState(AlarmSystem alarmSystem) {
        super(alarmSystem);
    }

    @Override
    public void activate(String code) {
        alarmSystem.setState(new ActiveAlarmSystemState(alarmSystem, code));
    }
}

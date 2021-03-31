package ru.sbt.mipt.oop;

public record InactiveAlarmSystemState(AlarmSystem alarmSystem) implements AlarmSystemState {

    @Override
    public void activate(String code) {
        setActiveAlarmSystemState(code);
    }

    private void setActiveAlarmSystemState(String code) {
        alarmSystem.setState(new ActiveAlarmSystemState(alarmSystem, code));
    }

}

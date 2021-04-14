package ru.sbt.mipt.oop;

public record AlarmInactiveState(Alarm alarmSystem) implements AlarmState {

    @Override
    public void activate(String code) {
        alarmSystem.setState(new AlarmActiveState(alarmSystem, code));
    }

    @Override
    public void panic() {
        alarmSystem.setState(new AlarmPanicState(alarmSystem, null, System.out::println));
    }

}

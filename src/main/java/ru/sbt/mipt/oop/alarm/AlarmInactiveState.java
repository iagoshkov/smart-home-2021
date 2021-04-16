package ru.sbt.mipt.oop.alarm;

public class AlarmInactiveState implements AlarmState {

    final private Alarm alarm;

    public AlarmInactiveState(Alarm alarm) {
        this.alarm = alarm;

        System.out.println("Alarm was deactivated");
    }

    @Override
    public void activate(String code) {
        alarm.setState(new AlarmActiveState(alarm, code));
    }

    @Override
    public void panic() {
        alarm.setState(new AlarmPanicState(alarm, null, System.out::println));
    }

}

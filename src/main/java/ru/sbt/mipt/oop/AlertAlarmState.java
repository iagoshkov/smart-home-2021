package ru.sbt.mipt.oop;

public class AlertAlarmState implements AlarmState {
    protected Alarm alarm;

    public AlertAlarmState(Alarm alarm) {

        this.alarm = alarm;
    }

    @Override
    public void activate(String code) {

    }

    @Override
    public void deactivate(String code) {
        if (alarm.codeIsCorrect(code)) {
            alarm.setState(new DeactivatedAlarmState(alarm));
        }
    }

    @Override
    public void alert() {

    }
}

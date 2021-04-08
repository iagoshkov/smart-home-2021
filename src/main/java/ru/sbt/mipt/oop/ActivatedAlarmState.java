package ru.sbt.mipt.oop;

public class ActivatedAlarmState implements AlarmState {
    protected Alarm alarm;

    public ActivatedAlarmState(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(String code) {

    }

    @Override
    public void deactivate(String code) {
        if (alarm.codeIsCorrect(code)) {
            alarm.setState(new DeactivatedAlarmState(alarm));
        } else {
            alarm.setState(new AlertAlarmState(alarm));
        }
    }

    @Override
    public void alert() {

        alarm.setState(new AlertAlarmState(alarm));
    }
}

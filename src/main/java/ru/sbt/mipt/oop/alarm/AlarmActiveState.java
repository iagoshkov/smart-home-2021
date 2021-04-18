package ru.sbt.mipt.oop.alarm;

import java.util.Objects;

public class AlarmActiveState implements AlarmState {

    final private Alarm alarm;
    final private String code;

    public AlarmActiveState(Alarm alarm, String code) {
        this.alarm = alarm;
        this.code = code;

        System.out.println("Alarm was activated");
    }

    @Override
    public void deactivate(String code) {
        if (isCodeCorrect(code)) {
            setInactiveState();
        } else {
            panic();
        }
    }

    @Override
    public void panic() {
        alarm.setState(new AlarmPanicState(alarm, this.code, System.out::println));
    }

    @Override
    public boolean allowSensorEvents() {
        panic();
        return false;
    }

    private boolean isCodeCorrect(String code) {
        return Objects.equals(this.code, code);
    }

    private void setInactiveState() {
        alarm.setState(new AlarmInactiveState(alarm));
    }

}

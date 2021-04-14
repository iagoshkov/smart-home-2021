package ru.sbt.mipt.oop;

import java.util.Objects;

public record AlarmActiveState(Alarm alarmSystem,
                               String code) implements AlarmState {

    @Override
    public void deactivate(String code) {
        if (isCodeCorrect(code)) {
            setInactiveState();
        } else {
            panic();
        }
    }

    @Override
    public boolean allowSensorEvents() {
        panic();
        return false;
    }

    @Override
    public void panic() {
        alarmSystem.setState(new AlarmPanicState(alarmSystem, this.code, System.out::println));
    }

    private boolean isCodeCorrect(String code) {
        return Objects.equals(this.code, code);
    }

    private void setInactiveState() {
        alarmSystem.setState(new AlarmInactiveState(alarmSystem));
    }

}

package ru.sbt.mipt.oop;

import java.util.Objects;

public record ActiveAlarmSystemState(AlarmSystem alarmSystem,
                                     String code) implements AlarmSystemState {

    @Override
    public void deactivate(String code) {
        if (Objects.equals(this.code, code)) {
            setInactiveState();
        } else {
            setPanicState(this.code);
        }
    }

    @Override
    public boolean allowSensorEvents() {
        setPanicState(this.code);
        return false;
    }

    @Override
    public void panic() {
        setPanicState(this.code);
    }

    private void setInactiveState() {
        alarmSystem.setState(new InactiveAlarmSystemState(alarmSystem));
    }

    private void setPanicState(String code) {
        alarmSystem.setState(new PanicAlarmSystemState(alarmSystem, code, System.out::println));
    }

}

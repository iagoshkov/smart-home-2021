package ru.sbt.mipt.oop;

public abstract class AlarmSystemState {

    protected final AlarmSystem alarmSystem;

    protected AlarmSystemState(AlarmSystem alarmSystem) {
        this.alarmSystem = alarmSystem;
    }

    public void activate(String code) {
        // do nothing
    }

    public void deactivate(String code) {
        // do nothing
    }

    public boolean allowSensorEvents() {
        return true;
    }

}

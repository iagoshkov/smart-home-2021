package ru.sbt.mipt.oop;

public class AlarmSystem {

    private AlarmSystemState state;

    public AlarmSystemState getState() {
        return state;
    }

    public void setState(AlarmSystemState state) {
        this.state = state;
    }

    public AlarmSystem(String code) {
        this.state = new ActiveAlarmSystemState(this, code);
    }

}

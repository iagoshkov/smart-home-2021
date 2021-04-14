package ru.sbt.mipt.oop.alarm;

public class Alarm implements AlarmState {

    private AlarmState state;

    void setState(AlarmState state) {
        this.state = state;
    }

    public Alarm(String code) {
        this.state = new AlarmActiveState(this, code);
    }

    @Override
    public void activate(String code) {
        state.activate(code);
    }

    @Override
    public void deactivate(String code) {
        state.deactivate(code);
    }

    @Override
    public boolean allowSensorEvents() {
        return state.allowSensorEvents();
    }

    @Override
    public void panic() { state.panic(); }
}

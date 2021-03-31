package ru.sbt.mipt.oop;

public class AlarmSystem implements AlarmSystemState {

    private AlarmSystemState state;

    void setState(AlarmSystemState state) {
        this.state = state;
    }

    public AlarmSystem(String code) {
        this.state = new ActiveAlarmSystemState(this, code);
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

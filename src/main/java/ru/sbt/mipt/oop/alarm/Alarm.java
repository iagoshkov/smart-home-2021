package ru.sbt.mipt.oop.alarm;

public class Alarm implements AlarmState {

    private AlarmState state;

    void setState(AlarmState state) {
        this.state = state;
    }

    public Alarm() {
        this.state = new AlarmInactiveState(this);
    }

    @Override
    public void activate(String code) {
        System.out.println("Alarm was activated");
        state.activate(code);
    }

    @Override
    public void deactivate(String code) {
        System.out.println("Alarm was deactivated");
        state.deactivate(code);
    }

    @Override
    public void panic() {
        System.out.println("Alarm was got panic state");
        state.panic();
    }

    @Override
    public boolean allowSensorEvents() {
        return state.allowSensorEvents();
    }

}

package ru.sbt.mipt.oop.alarm;

public class Alarm {

    private AlarmStateInterface state;
    private int code;

    public Alarm(int code) {
        this.state = new DeactivatedState(this);
        this.code = code;
    }

    public void changeState(AlarmStateInterface state) {
        this.state = state;
    }

    public AlarmStateInterface getState() {
        return state;
    }

    int getCode() {
        return code;
    }

    public void activate(int code){
        state.activate(code);
    }

    public void deactivate(int code){
        state.deactivate(code);
    }

    public void alarm(){
        state.alarm();
    }
}

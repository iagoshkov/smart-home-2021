package ru.sbt.mipt.oop.alarm;

public class Alarm {

    private AlarmStates state;
    private int code;

    public Alarm(int code) {
        this.state = new DeactivatedState(this);
        this.code = code;
    }

    public void changeState(AlarmStates state) {
        this.state = state;
    }

    public AlarmStates getState() {
        return state;
    }

    public int getCode() {
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

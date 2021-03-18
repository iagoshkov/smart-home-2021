package ru.sbt.mipt.oop;

public class Light implements Actionable{
    private boolean isOn;

    public String getId() {
        return id;
    }

    private String id;

    public Light(String id, boolean isOn) {
        this.id = id;
        this.isOn = isOn;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    @Override
    public void execute(Action action) {
        action.execute(this);
    }
}

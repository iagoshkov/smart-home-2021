package ru.sbt.mipt.oop.Objects.HomeObjects;


public class Light extends HomeObject {
  
    private boolean isOn;
  
    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    public Light(String id, boolean isOn) {
        super(id);
        this.isOn = isOn;
    }
}

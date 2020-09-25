package ru.sbt.mipt.oop;

public class Light extends SmartDevice {
    private boolean isOn;

    public Light(String id, boolean isOn, Room location) throws IllegalArgumentException {
        super(id, location, SmartDeviceType.LIGHT);
        this.isOn = isOn;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Room getLocation() {
        return location;
    }

    @Override
    public SmartDeviceType getType() {
        return type;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }
}

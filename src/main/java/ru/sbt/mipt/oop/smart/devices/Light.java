package ru.sbt.mipt.oop.smart.devices;

import ru.sbt.mipt.oop.smart.home.Room;

public class Light extends SmartDevice {
    private boolean isOn;

    public Light(String id, boolean isOn, Room location) throws IllegalArgumentException {
        super(id, location, SmartDeviceType.LIGHT);
        this.isOn = isOn;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }
}

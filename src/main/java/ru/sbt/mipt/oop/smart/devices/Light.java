package ru.sbt.mipt.oop.smart.devices;

import ru.sbt.mipt.oop.smart.actions.Action;
import ru.sbt.mipt.oop.smart.home.locations.Location;
import ru.sbt.mipt.oop.smart.home.locations.Room;

public class Light implements SmartDevice {
    private final String id;
    private final Room location;
    private final SmartDeviceType type;
    private boolean isOn;

    public Light(String id, boolean isOn, Room location) throws IllegalArgumentException {
        if (id == null || location == null) throw new IllegalArgumentException();

        this.id = id;
        this.location = location;
        this.isOn = isOn;
        this.type = SmartDeviceType.LIGHT;
    }

    public boolean isOn() {
        return this.isOn;
    }

    public void setOn(boolean on) {
        this.isOn = on;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public SmartDeviceType getType() {
        return type;
    }

    @Override
    public void execute(Action action) {
        action.act(this);
    }
}
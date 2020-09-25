package ru.sbt.mipt.oop.smart.devices;

import ru.sbt.mipt.oop.smart.home.Room;

public abstract class SmartDevice {
    protected final String id;
    protected final Room location;
    protected final SmartDeviceType type;

    protected SmartDevice(String id, Room location, SmartDeviceType type) {
        if (id == null || id.isEmpty() || location == null || type == null) {
            throw new IllegalArgumentException("Invalid construction arguments");
        }

        this.id = id;
        this.location = location;
        this.type = type;
    }

    public String getId() { return id; }
    public Room getLocation() { return location; }
    public SmartDeviceType getType() { return type; }
}

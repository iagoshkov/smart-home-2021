package ru.sbt.mipt.oop.smart.devices;

import ru.sbt.mipt.oop.smart.home.Room;

public class Door extends SmartDevice {
    private boolean isOpen;

    public Door(String id, boolean isOpen, Room location) {
        super(id, location, SmartDeviceType.DOOR);
        this.isOpen = isOpen;
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

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}

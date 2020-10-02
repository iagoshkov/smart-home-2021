package ru.sbt.mipt.oop.smart.devices;

import ru.sbt.mipt.oop.smart.actions.Action;
import ru.sbt.mipt.oop.smart.home.locations.Location;
import ru.sbt.mipt.oop.smart.home.locations.Room;

public class Door implements SmartDevice {
    private final String id;
    private final Room location;
    private final SmartDeviceType type;
    private boolean isOpen;

    public Door(String id, boolean isOpen, Room location) throws IllegalArgumentException {
        if (id == null || location == null) throw new IllegalArgumentException();

        this.id = id;
        this.location = location;
        this.isOpen = isOpen;
        this.type = SmartDeviceType.DOOR;
    }

    public boolean isOpen() {
        return this.isOpen;
    }

    public void setOpen(boolean open) {
        this.isOpen = open;
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
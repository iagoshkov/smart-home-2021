package ru.sbt.mipt.oop.smart.home;

import ru.sbt.mipt.oop.events.actions.Action;
import ru.sbt.mipt.oop.smart.devices.Actionable;
import ru.sbt.mipt.oop.smart.devices.SmartDevice;

import java.util.*;

public class Room implements Actionable {
    private final String name;
    private final Set<SmartDevice> devices = new HashSet<>();

    public Room(String name, Collection<SmartDevice> devices) throws IllegalArgumentException {
        if (name == null) throw new IllegalArgumentException();
        this.name = name;

        if (devices != null) this.devices.addAll(devices);
    }

    public void addDevice(SmartDevice device) throws IllegalArgumentException {
        if (device == null) throw new IllegalArgumentException();
        devices.add(device);
    }

    @Override
    public void execute(Action action) {
        if (action == null) return;
        for(SmartDevice device : devices) {
            ((Actionable) device).execute(action);
        }
    }

    public String getName() {
        return name;
    }

    public Collection<SmartDevice> getDevices() {
        return devices;
    }
}
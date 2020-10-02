package ru.sbt.mipt.oop.smart.home;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import ru.sbt.mipt.oop.smart.actions.Action;
import ru.sbt.mipt.oop.smart.devices.SmartDevice;
import ru.sbt.mipt.oop.smart.devices.SmartDeviceType;
import ru.sbt.mipt.oop.smart.home.locations.Location;

public class SmartHome implements SmartDevice, Location {
    private final String id;
    private final String name;
    private final Location location;
    private final SmartDeviceType type;

    private final Map<String, SmartDevice> devices;
    private final Map<Location, ArrayList<SmartDevice>> devicesLocations;

    public SmartHome(String id, String name) throws IllegalArgumentException {
        if (id == null || name == null) throw new IllegalArgumentException();

        this.id = id;
        this.name = name;
        this.location = this;
        this.type = SmartDeviceType.SMART_HOME;

        devices = new HashMap<>();
        devicesLocations = new HashMap<>();
    }

    public void addDevice(SmartDevice device) throws IllegalArgumentException {
        if (device.getId().equals(id) || devices.containsKey(device.getId())) {
            throw new IllegalArgumentException("The device id is already exists.");
        }
        devices.put(device.getId(), device);

        Location location = device.getLocation();
        if (!devicesLocations.containsKey(location)) {
            devicesLocations.put(location, new ArrayList<>());
        }
        devicesLocations.get(location).add(device);
    }

    public SmartDevice getDevice(String deviceId) {
        if (deviceId.equals(id)) return this;
        return devices.get(deviceId);
    }

    public Collection<SmartDevice> getAllDevices() {
        return devices.values();
    }

    public Collection<SmartDevice> getAllDevices(Location location) {
        if (location == null || !devicesLocations.containsKey(location)) return new ArrayList<>();
        return devicesLocations.get(location);
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
    public String getLocationName() {
        return name;
    }

    @Override
    public void execute(Action action) {
        action.act(this);
    }
}
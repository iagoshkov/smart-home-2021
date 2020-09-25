package ru.sbt.mipt.oop;

import java.util.*;

public class SmartHome {
    Map<String, SmartDevice> devices;

    public SmartHome() {
        devices = new HashMap<>();
    }

    public void addDevice(SmartDevice device) throws IllegalArgumentException {
        if (devices.containsKey(device.getId())) {
            throw new IllegalArgumentException("The device id is already exists.");
        }

        devices.put(device.getId(), device);
    }

    public SmartDevice getDevice(String deviceId) {
        return devices.get(deviceId);
    }

    public void turnOffAllLights() {
        devices.entrySet().stream()
                .filter(d -> d.getValue().getType().equals(SmartDeviceType.LIGHT))
                .forEach(d -> ((Light) d.getValue()).setOn(false));
    }
}

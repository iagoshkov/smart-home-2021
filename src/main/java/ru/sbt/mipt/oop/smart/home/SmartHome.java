package ru.sbt.mipt.oop.smart.home;

import ru.sbt.mipt.oop.smart.actions.ActionLightOff;
import ru.sbt.mipt.oop.smart.devices.SmartDevice;
import ru.sbt.mipt.oop.smart.devices.SmartDeviceType;

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
                .forEach(d -> new ActionLightOff().act(d.getValue()));
    }
}

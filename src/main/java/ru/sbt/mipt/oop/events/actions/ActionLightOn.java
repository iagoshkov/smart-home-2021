package ru.sbt.mipt.oop.events.actions;

import ru.sbt.mipt.oop.smart.devices.Light;
import ru.sbt.mipt.oop.smart.devices.SmartDevice;

public class ActionLightOn implements Action {
    private final String deviceId;

    public ActionLightOn(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String getDeviceId() {
        return deviceId;
    }

    @Override
    public void act(SmartDevice smartDevice) {
        if (!(smartDevice instanceof Light)) return;
        if (deviceId != null && !smartDevice.getId().equals(deviceId)) return;
        Light light = (Light) smartDevice;
        light.setOn(true);

        System.out.println("Light " + light.getId() + " on");
    }
}
package ru.sbt.mipt.oop.smart.actions;

import ru.sbt.mipt.oop.smart.devices.Light;
import ru.sbt.mipt.oop.smart.devices.SmartDevice;
import ru.sbt.mipt.oop.smart.devices.SmartDeviceType;

public class ActionLightOn implements Action {
    @Override
    public void act(SmartDevice smartDevice) {
        if (smartDevice == null || smartDevice.getType() != SmartDeviceType.LIGHT) return;
        Light light = (Light) smartDevice;
        light.setOn(true);
        System.out.println("Light " + light.getId() + " in room " + light.getLocation().getLocationName() + " was turned off");
    }
}
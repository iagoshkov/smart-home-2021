package ru.sbt.mipt.oop.smart.actions;

import ru.sbt.mipt.oop.smart.devices.Light;
import ru.sbt.mipt.oop.smart.devices.SmartDevice;
import ru.sbt.mipt.oop.smart.devices.SmartDeviceType;

public class ActionLightOff implements Actionable {

    @Override
    public void act(SmartDevice device) {
        if (device.getType() != SmartDeviceType.LIGHT) {
            System.out.printf("The device type '%s' does not match the event action 'Light off'%n", device.getType().toString());
            return;
        }

        ((Light) device).setOn(false);
        System.out.println("Light " + device.getId() + " in room " + device.getLocation().getName() + " was turned off");
    }
}

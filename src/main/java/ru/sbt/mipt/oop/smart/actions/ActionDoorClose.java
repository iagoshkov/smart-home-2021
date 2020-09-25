package ru.sbt.mipt.oop.smart.actions;

import ru.sbt.mipt.oop.smart.devices.Door;
import ru.sbt.mipt.oop.smart.devices.SmartDevice;
import ru.sbt.mipt.oop.smart.devices.SmartDeviceType;

public class ActionDoorClose implements Actionable {

    @Override
    public void act(SmartDevice device) {
        if (device.getType() != SmartDeviceType.DOOR) {
            System.out.printf("The device type '%s' does not match the event action 'Door close'%n", device.getType().toString());
            return;
        }

        ((Door) device).setOpen(false);
        System.out.println("Door " + device.getId() + " in room " + device.getLocation().getName() + " was closed");
    }
}

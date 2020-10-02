package ru.sbt.mipt.oop.smart.actions;

import ru.sbt.mipt.oop.smart.devices.Door;
import ru.sbt.mipt.oop.smart.devices.SmartDevice;
import ru.sbt.mipt.oop.smart.devices.SmartDeviceType;

public class ActionDoorClose implements Action {
    @Override
    public void act(SmartDevice smartDevice) {
        if (smartDevice == null || smartDevice.getType() != SmartDeviceType.DOOR) return;
        Door door = (Door) smartDevice;
        door.setOpen(false);
        System.out.println("Door " + door.getId() + " in room " + door.getLocation().getLocationName() + " was closed");
    }
}
package ru.sbt.mipt.oop.events.actions;

import ru.sbt.mipt.oop.smart.devices.Door;
import ru.sbt.mipt.oop.smart.devices.SmartDevice;

public class ActionDoorOpen implements Action {
    private final String deviceId;

    public ActionDoorOpen(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String getDeviceId() {
        return deviceId;
    }

    @Override
    public void act(SmartDevice smartDevice) {
        if (!(smartDevice instanceof Door)) return;
        if (deviceId != null && !smartDevice.getId().equals(deviceId)) return;
        Door door = (Door) smartDevice;
        door.setOpen(true);

        System.out.println("Door " + door.getId() + " opened");
    }
}

package ru.sbt.mipt.oop.smart.actions;

import ru.sbt.mipt.oop.smart.devices.Door;
import ru.sbt.mipt.oop.smart.devices.SmartDevice;
import ru.sbt.mipt.oop.smart.devices.SmartDeviceType;
import ru.sbt.mipt.oop.smart.home.SmartHome;
import ru.sbt.mipt.oop.smart.home.locations.Room;

public class ActionCloseAllDoorsInRoom implements Action {
    private final Room room;

    public ActionCloseAllDoorsInRoom(Room room) {
        this.room = room;
    }

    @Override
    public void act(SmartDevice smartDevice) {
        if (smartDevice == null || smartDevice.getType() != SmartDeviceType.SMART_HOME) return;
        SmartHome smartHome = (SmartHome)smartDevice;
        var doorsInRoom = smartHome
                                    .getAllDevices(room)
                                    .stream().filter(d -> d.getType() == SmartDeviceType.DOOR)
                                    .toArray();
        for (var device : doorsInRoom) {
            Door door = (Door) device;
            door.setOpen(false);
        }
    }
}

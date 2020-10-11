package ru.sbt.mipt.oop.smart.home;

import java.util.*;
import java.util.stream.Collectors;

import ru.sbt.mipt.oop.events.actions.Action;
import ru.sbt.mipt.oop.smart.devices.Actionable;
import ru.sbt.mipt.oop.smart.devices.SmartDevice;

public class SmartHome implements Actionable {
    private final Set<Room> rooms = new HashSet<>();

    public SmartHome(Collection<Room> rooms) throws IllegalArgumentException {
        if (rooms == null) throw new IllegalArgumentException();
        this.rooms.addAll(rooms);
    }

    public Room getDeviceRoom(String deviceId) {
        if (deviceId == null) return null;
        for (Room room : rooms) {
            Collection<String> devicesIdsInRoom = room.getDevices()
                    .stream()
                    .map(SmartDevice::getId)
                    .collect(Collectors.toList());

            if (devicesIdsInRoom.contains(deviceId)) return room;
        }
        return null;
    }

    @Override
    public void execute(Action action) {
        if (action == null) return;
        for (Room room : rooms)
            ((Actionable)room).execute(action);
    }
}
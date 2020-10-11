package ru.sbt.mipt.oop.smart.home;

import java.util.*;
import java.util.stream.Collectors;

import ru.sbt.mipt.oop.Constants;
import ru.sbt.mipt.oop.events.actions.Action;
import ru.sbt.mipt.oop.events.actions.ActionDecorator;
import ru.sbt.mipt.oop.smart.devices.Actionable;
import ru.sbt.mipt.oop.smart.devices.Alarm;
import ru.sbt.mipt.oop.smart.devices.SmartDevice;

public class SmartHome implements Actionable {
    private final Set<Room> rooms = new HashSet<>();
    private final Alarm alarm;

    public SmartHome(Alarm alarm, Collection<Room> rooms) throws IllegalArgumentException {
        if (alarm == null || rooms == null) throw new IllegalArgumentException();
        this.rooms.addAll(rooms);
        this.alarm = alarm;
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
        alarm.execute(action);

        ActionDecorator decoratorAction = new ActionDecorator(!alarm.isAlert(), action);
        for (Room room : rooms)
            ((Actionable)room).execute(decoratorAction);
    }
}
package ru.sbt.mipt.oop.api.adapter;

import ru.sbt.mipt.oop.event_handlers.SensorEventType;

public class DoorSensorEventGetterImpl implements SensorEventGetter {
    @Override
    public SensorEventType getType(String name) {
        if (name.equals("DoorIsOpen")) return SensorEventType.DOOR_OPEN;
        if (name.equals("DoorIsClosed")) return SensorEventType.DOOR_CLOSED;
        if (name.equals("DoorIsLocked")) return SensorEventType.DOOR_LOCKED;
        if (name.equals("DoorIsUnlocked")) return SensorEventType.DOOR_UNLOCKED;
        return null;
    }
}

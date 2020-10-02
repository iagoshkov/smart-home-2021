package ru.sbt.mipt.oop.smart.devices;

import ru.sbt.mipt.oop.smart.home.locations.Location;

public interface SmartDevice extends Actionable {
    String getId();
    Location getLocation();
    SmartDeviceType getType();
}
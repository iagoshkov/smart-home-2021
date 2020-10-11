package ru.sbt.mipt.oop.events.actions;

import ru.sbt.mipt.oop.smart.devices.SmartDevice;

public interface Action {
    String getDeviceId();
    void act(SmartDevice smartDevice);
}
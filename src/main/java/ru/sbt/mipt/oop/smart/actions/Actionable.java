package ru.sbt.mipt.oop.smart.actions;

import ru.sbt.mipt.oop.smart.devices.SmartDevice;

public interface Actionable {
    void act(SmartDevice device);
}

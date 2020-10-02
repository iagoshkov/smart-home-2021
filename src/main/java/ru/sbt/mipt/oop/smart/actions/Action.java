package ru.sbt.mipt.oop.smart.actions;

import ru.sbt.mipt.oop.smart.devices.SmartDevice;

public interface Action {
    void act(SmartDevice smartDevice);
}
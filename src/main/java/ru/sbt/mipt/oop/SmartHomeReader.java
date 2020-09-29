package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.devices.SmartHome;

import java.io.IOException;

public interface SmartHomeReader {
    public SmartHome read() throws IOException;
}

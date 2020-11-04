package ru.sbt.mipt.oop;

import java.io.IOException;

public interface ISmartHomeLoader {
    SmartHome load() throws RuntimeException, IOException;
}

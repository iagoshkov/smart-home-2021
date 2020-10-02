package ru.sbt.mipt.oop.smart.home.utils;

import ru.sbt.mipt.oop.smart.home.SmartHome;

public interface SmartHomeReaderWriter {
    SmartHome loadSmartHome() throws RuntimeException;
    void saveSmartHome(SmartHome smartHome) throws RuntimeException;
}
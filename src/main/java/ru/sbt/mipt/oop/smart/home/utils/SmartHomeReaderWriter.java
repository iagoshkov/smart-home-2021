package ru.sbt.mipt.oop.smart.home.utils;

import ru.sbt.mipt.oop.smart.home.SmartHome;

import java.io.IOException;

public interface SmartHomeReaderWriter {
    SmartHome loadSmartHome() throws IOException;
    void saveSmartHome(SmartHome smartHome) throws IOException;
}
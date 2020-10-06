package ru.sbt.mipt.oop;

import java.io.IOException;

public interface SmartHomeReaderWriter {
    SmartHome loadSmartHome() throws RuntimeException, IOException;

    void saveSmartHome(SmartHome smartHome) throws RuntimeException;
}

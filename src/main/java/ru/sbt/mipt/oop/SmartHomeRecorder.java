package ru.sbt.mipt.oop;


import java.io.IOException;

public interface SmartHomeRecorder {
    SmartHome getSmartHome(String path) throws IOException;
    void saveSmartHome(SmartHome smartHome, String path) throws IOException;
}

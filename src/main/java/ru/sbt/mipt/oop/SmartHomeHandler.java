package ru.sbt.mipt.oop;

interface SmartHomeHandler {
    void handleEvent(SensorEvent event);
    void setSmartHome(SmartHome smartHome);
    void setOutputStream(OutputStream output);
}

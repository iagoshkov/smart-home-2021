package ru.sbt.mipt.oop;

public class SmartHomeTestComponent {

    public SmartHome smartHome = null;
    public SmartHomeEventHandler eventHandler = null;

    public void set(SmartHome smartHome, SmartHomeEventHandler eventHandler) {
        this.smartHome = smartHome;
        this.eventHandler = eventHandler;
    }

}

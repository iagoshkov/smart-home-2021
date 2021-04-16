package ru.sbt.mipt.oop.util;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.event.handler.EventHandler;

public class SmartHomeTestComponent {

    public SmartHome smartHome = null;
    public EventHandler eventHandler = null;

    public void set(SmartHome smartHome, EventHandler eventHandler) {
        this.smartHome = smartHome;
        this.eventHandler = eventHandler;
    }

}

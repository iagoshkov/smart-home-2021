package ru.sbt.mipt.oop.util;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.SmartHomeEventHandler;

public class SmartHomeTestComponent {

    public SmartHome smartHome = null;
    public SmartHomeEventHandler eventHandler = null;

    public void set(SmartHome smartHome, SmartHomeEventHandler eventHandler) {
        this.smartHome = smartHome;
        this.eventHandler = eventHandler;
    }

}

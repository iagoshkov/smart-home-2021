package ru.sbt.mipt.oop.util;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.event.processor.EventProcessor;

public class SmartHomeTestComponent {

    public SmartHome smartHome = null;
    public EventProcessor eventProcessor = null;

    public void set(SmartHome smartHome, EventProcessor eventProcessor) {
        this.smartHome = smartHome;
        this.eventProcessor = eventProcessor;
    }

}

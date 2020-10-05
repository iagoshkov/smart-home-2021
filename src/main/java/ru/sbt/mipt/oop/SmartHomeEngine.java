package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.events.*;

public class SmartHomeEngine implements Engine {
    EventProcessorComposite processorComposite;
    SmartHome smartHome;

    public SmartHomeEngine(SmartHome home) {
        smartHome = home;
        processorComposite = new EventProcessorComposite();
        processorComposite.addEventProcessor(EventProcessorType.DOOR, new DoorEventProcessor());
        processorComposite.addEventProcessor(EventProcessorType.LIGHT, new LightEventProcessor());
    }

    public void start() {
        // начинаем цикл обработки событий
        SensorEvent event = null;
        do {
            event = processorComposite.processEvent(smartHome, event);
        } while (event != null);
    }
}

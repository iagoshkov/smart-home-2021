package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.elements.SmartHome;
import ru.sbt.mipt.oop.events.*;
import ru.sbt.mipt.oop.events.processors.DoorEventProcessor;
import ru.sbt.mipt.oop.events.processors.EventProcessorComposite;
import ru.sbt.mipt.oop.events.processors.EventProcessorType;
import ru.sbt.mipt.oop.events.processors.LightEventProcessor;

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
        Event event = null;
        do {
            event = processorComposite.processEvent(smartHome, event);
        } while (event != null);
    }
}

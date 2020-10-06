package ru.sbt.mipt.oop.events.processors;

import ru.sbt.mipt.oop.elements.HomeComponent;
import ru.sbt.mipt.oop.elements.Light;
import ru.sbt.mipt.oop.elements.SmartHome;
import ru.sbt.mipt.oop.events.Event;

import static ru.sbt.mipt.oop.events.typedefs.LightEventType.LIGHT_ON;

public class LightEventProcessor implements EventProcessor {
    public Event processEvent(SmartHome smartHome, Event event) {
        smartHome.apply(event, (HomeComponent c) -> ((Light)c).setActive(event.getType() == LIGHT_ON));
        return event;
    }
}

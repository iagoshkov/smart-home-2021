package ru.sbt.mipt.oop.events.processors;

import ru.sbt.mipt.oop.elements.HomeComponent;
import ru.sbt.mipt.oop.elements.Light;
import ru.sbt.mipt.oop.elements.SmartHome;
import ru.sbt.mipt.oop.events.Event;

import static ru.sbt.mipt.oop.events.typedefs.HallDoorEventType.LIGHTS_ON;

public class HallDoorEventProcessor implements EventProcessor {
    @Override
    public Event processEvent(SmartHome smartHome, Event event) {
        smartHome.apply(event, (HomeComponent c) -> ((Light)c).setActive(event.getType() == LIGHTS_ON));
        return event;
    }
}

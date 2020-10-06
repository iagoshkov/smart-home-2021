package ru.sbt.mipt.oop.events.processors;

import ru.sbt.mipt.oop.actions.HallAction;
import ru.sbt.mipt.oop.elements.SmartHome;
import ru.sbt.mipt.oop.commands.SensorCommand;
import ru.sbt.mipt.oop.elements.Light;
import ru.sbt.mipt.oop.elements.Room;
import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.HallDoorEvent;

import static ru.sbt.mipt.oop.events.typedefs.HallDoorEventType.LIGHTS_ON;

public class HallDoorEventProcessor implements EventProcessor {
    @Override
    public Event processEvent(SmartHome smartHome, Event event) {
        smartHome.apply(new HallAction(event.getType() == LIGHTS_ON), smartHome.getId());
        return event;
    }
}

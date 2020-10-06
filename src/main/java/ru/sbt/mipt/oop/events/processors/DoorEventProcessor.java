package ru.sbt.mipt.oop.events.processors;

import ru.sbt.mipt.oop.commands.CommandType;
import ru.sbt.mipt.oop.commands.SimpleSensorCommand;
import ru.sbt.mipt.oop.elements.Door;
import ru.sbt.mipt.oop.elements.HomeComponent;
import ru.sbt.mipt.oop.elements.SmartHome;
import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.HallDoorEvent;
import ru.sbt.mipt.oop.events.typedefs.HallDoorEventType;

import static ru.sbt.mipt.oop.events.typedefs.DoorEventType.DOOR_OPEN;

public class DoorEventProcessor implements EventProcessor {
    public Event processEvent(SmartHome smartHome, Event event) {
        Event inputEvent = event;
        event = smartHome.apply(inputEvent, ((HomeComponent c) -> ((Door)c).setActive(inputEvent.getType() == DOOR_OPEN)));
        if (event != inputEvent && event.getType() instanceof HallDoorEventType) {
            event = new HallDoorEvent(HallDoorEventType.LIGHTS_OFF, smartHome.getId(), new SimpleSensorCommand(CommandType.LIGHT_OFF, smartHome.getId()));
        }
        return event;
    }
}

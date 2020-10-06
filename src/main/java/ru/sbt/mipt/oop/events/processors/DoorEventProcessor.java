package ru.sbt.mipt.oop.events.processors;

import ru.sbt.mipt.oop.actions.Action;
import ru.sbt.mipt.oop.actions.ActionType;
import ru.sbt.mipt.oop.actions.DoorAction;
import ru.sbt.mipt.oop.commands.CommandType;
import ru.sbt.mipt.oop.commands.SimpleSensorCommand;
import ru.sbt.mipt.oop.elements.SmartHome;
import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.HallDoorEvent;
import ru.sbt.mipt.oop.events.typedefs.HallDoorEventType;

import static ru.sbt.mipt.oop.events.typedefs.DoorEventType.DOOR_OPEN;

public class DoorEventProcessor implements EventProcessor {
    public Event processEvent(SmartHome smartHome, Event event) {
        Action action = smartHome.apply(new DoorAction(event.getType() == DOOR_OPEN), event.getObjectId());
        if (action != null && action.getType() == ActionType.HALL) {
            event = new HallDoorEvent(HallDoorEventType.LIGHTS_OFF, smartHome.getId(), new SimpleSensorCommand(CommandType.LIGHT_OFF, smartHome.getId()));
        }
        return event;
    }
}

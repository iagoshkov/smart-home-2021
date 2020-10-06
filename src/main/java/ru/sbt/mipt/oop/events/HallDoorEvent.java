package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.commands.SensorCommand;
import ru.sbt.mipt.oop.elements.ComponentId;
import ru.sbt.mipt.oop.events.typedefs.EventType;

public class HallDoorEvent extends SensorEvent {
    private SensorCommand command;

    public HallDoorEvent(EventType type, ComponentId objectId, SensorCommand command) {
        super(type, objectId);
        this.command = command;
    }

    public SensorCommand getCommand() {
        return command;
    }

    public void setCommand(SensorCommand command) {
        this.command = command;
    }
}

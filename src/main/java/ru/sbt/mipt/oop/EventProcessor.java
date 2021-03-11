package ru.sbt.mipt.oop;

import java.util.Map;

public abstract class EventProcessor {

    protected final Map<SensorEventType, EventHandler> eventHandlerByEventType;
    protected final Map<CommandType, CommandProducer> commandHandlerByCommandType;

    public EventProcessor(Map<SensorEventType, EventHandler> eventHandlerByEventType,
                              Map<CommandType, CommandProducer> commandHandlerByCommandType) {
        this.eventHandlerByEventType = eventHandlerByEventType;
        this.commandHandlerByCommandType = commandHandlerByCommandType;
    }

    abstract public void processEvent(SmartHome smartHome, SensorEvent event);

    protected CommandType handleEvent(SensorEvent event, Room room, Light light, Door door) {
        if (eventHandlerByEventType.containsKey(event.getType())) {
            return eventHandlerByEventType.get(event.getType()).handleEvent(event, room, light, door);
        }
        return null;
    }

    protected void handleCommand(CommandType command, SmartHome smartHome) {
        if (commandHandlerByCommandType.containsKey(command)) {
            commandHandlerByCommandType.get(command).produceCommand(command, smartHome);
        }
    }

}

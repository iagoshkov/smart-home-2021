package ru.sbt.mipt.oop;

import java.util.Map;

public class LightEventProcessor implements EventProcessor {

    private final Map<SensorEventType, EventHandler> eventHandlerByEventType;
    private final Map<CommandType, CommandProducer> commandHandlerByCommandType;

    public LightEventProcessor(Map<SensorEventType, EventHandler> eventHandlerByEventType,
                               Map<CommandType, CommandProducer> commandHandlerByCommandType) {
        this.eventHandlerByEventType = eventHandlerByEventType;
        this.commandHandlerByCommandType = commandHandlerByCommandType;
    }

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if (!eventHandlerByEventType.containsKey(event.getType())) return;

        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                CommandType command = handleEvent(event, room, light);
                if (command != null) {
                    handleCommand(command, smartHome);
                }
            }
        }
    }

    private CommandType handleEvent(SensorEvent event, Room room, Light light) {
        if (!light.getId().equals(event.getObjectId())) return null;

        if (eventHandlerByEventType.containsKey(event.getType())) {
            return eventHandlerByEventType.get(event.getType()).handleEvent(room, light, null);
        }
        return null;
    }

    private void handleCommand(CommandType command, SmartHome smartHome) {
        if (commandHandlerByCommandType.containsKey(command)) {
            commandHandlerByCommandType.get(command).produceCommand(command, smartHome);
        }
    }

}

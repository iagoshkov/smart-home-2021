package ru.sbt.mipt.oop;

import java.util.Map;

public class DoorEventProcessor implements EventProcessor {

    private final Map<SensorEventType, EventHandler> eventHandlerByEventType;
    private final Map<CommandType, CommandProducer> commandHandlerByCommandType;

    public DoorEventProcessor(Map<SensorEventType, EventHandler> eventHandlerByEventType,
                              Map<CommandType, CommandProducer> commandHandlerByCommandType) {
        this.eventHandlerByEventType = eventHandlerByEventType;
        this.commandHandlerByCommandType = commandHandlerByCommandType;
    }

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if (!eventHandlerByEventType.containsKey(event.getType())) return;

        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                CommandType command = handleEvent(event, room, door);
                if (command != null) {
                    handleCommand(command, smartHome);
                }
            }
        }
    }

    private CommandType handleEvent(SensorEvent event, Room room, Door door) {
        if (!door.getId().equals(event.getObjectId())) return null;

        if (eventHandlerByEventType.containsKey(event.getType())) {
            return eventHandlerByEventType.get(event.getType()).handleEvent(room, null, door);
        }
        return null;
    }

    private void handleCommand(CommandType command, SmartHome smartHome) {
        if (commandHandlerByCommandType.containsKey(command)) {
            commandHandlerByCommandType.get(command).produceCommand(command, smartHome);
        }
    }

}

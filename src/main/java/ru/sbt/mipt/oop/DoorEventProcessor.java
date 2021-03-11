package ru.sbt.mipt.oop;

import java.util.Map;

public class DoorEventProcessor extends EventProcessor {

    public DoorEventProcessor(Map<SensorEventType, EventHandler> eventHandlerByEventType,
                              Map<CommandType, CommandProducer> commandHandlerByCommandType) {
        super(eventHandlerByEventType, commandHandlerByCommandType);
    }

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if (!eventHandlerByEventType.containsKey(event.getType())) return;

        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                CommandType command = handleEvent(event, room, null, door);
                if (command != null) {
                    handleCommand(command, smartHome);
                }
            }
        }
    }

}

package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DoorEventProcessor implements EventProcessor {

    private final Map<SensorEventType, EventHandler> eventHandlerByEventType;

    public DoorEventProcessor(Map<SensorEventType, EventHandler> eventHandlerByEventType) {
        this.eventHandlerByEventType = eventHandlerByEventType;
    }

    @Override
    public List<CommandType> processEvent(SmartHome smartHome, SensorEvent event) {
        if (!eventHandlerByEventType.containsKey(event.getType())) return new ArrayList<>();

        List<CommandType> commandTypes = new ArrayList<>();

        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                CommandType command = handleEvent(event, room, door);
                if (command != null) {
                    commandTypes.add(command);
                }
            }
        }
        return commandTypes;
    }

    private CommandType handleEvent(SensorEvent event, Room room, Door door) {
        if (!door.getId().equals(event.getObjectId())) return null;

        if (eventHandlerByEventType.containsKey(event.getType())) {
            return eventHandlerByEventType.get(event.getType()).handleEvent(room, null, door);
        }
        return null;
    }

}

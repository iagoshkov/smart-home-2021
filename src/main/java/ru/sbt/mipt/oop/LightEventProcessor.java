package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LightEventProcessor implements EventProcessor {

    private final Map<SensorEventType, EventHandler> eventHandlerByEventType;

    public LightEventProcessor(Map<SensorEventType, EventHandler> eventHandlerByEventType) {
        this.eventHandlerByEventType = eventHandlerByEventType;
    }

    @Override
    public List<CommandType> processEvent(SmartHome smartHome, SensorEvent event) {
        if (!eventHandlerByEventType.containsKey(event.getType())) return new ArrayList<>();

        List<CommandType> commandTypes = new ArrayList<>();

        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                CommandType command = handleEvent(event, room, light);
                if (command != null) {
                    commandTypes.add(command);
                }
            }
        }
        return commandTypes;
    }

    private CommandType handleEvent(SensorEvent event, Room room, Light light) {
        if (!light.getId().equals(event.getObjectId())) return null;

        if (eventHandlerByEventType.containsKey(event.getType())) {
            return eventHandlerByEventType.get(event.getType()).handleEvent(room, light, null);
        }
        return null;
    }

}

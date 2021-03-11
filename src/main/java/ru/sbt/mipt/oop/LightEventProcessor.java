package ru.sbt.mipt.oop;

import java.util.Map;

public class LightEventProcessor extends EventProcessor {

    public LightEventProcessor(Map<SensorEventType, EventHandler> eventHandlerByEventType,
                               Map<CommandType, CommandProducer> commandHandlerByCommandType) {
        super(eventHandlerByEventType, commandHandlerByCommandType);
    }

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if (!eventHandlerByEventType.containsKey(event.getType())) return;

        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                CommandType command = handleEvent(event, room, light, null);
                if (command != null) {
                    handleCommand(command, smartHome);
                }
            }
        }
    }

}

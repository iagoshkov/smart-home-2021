package ru.sbt.mipt.oop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventProcessor {

    protected final Map<SensorEventType, EventHandler> eventHandlerByEventType;
    protected final Map<CommandType, CommandProducer> commandProducerByCommandType;

    public EventProcessor(Map<SensorEventType, EventHandler> eventHandlerByEventType,
                          Map<CommandType, CommandProducer> commandProducerByCommandType) {
        this.eventHandlerByEventType = eventHandlerByEventType != null ? eventHandlerByEventType : new HashMap<>();
        this.commandProducerByCommandType = commandProducerByCommandType != null ? commandProducerByCommandType : new HashMap<>();
    }

    public void processEvent(SmartHome smartHome, SensorEvent event) {
        EventHandler eventHandler = eventHandlerByEventType.getOrDefault(event.getType(), null);

        if (eventHandler != null) {
            List<CommandType> commandTypes = eventHandler.handleEvent(smartHome, event);

            for (CommandType commandType : commandTypes) {
                CommandProducer commandProducer = commandProducerByCommandType.getOrDefault(commandType, null);

                if (commandProducerByCommandType.containsKey(commandType)) {
                    commandProducer.produceCommand(smartHome, commandType);
                }
            }
        }
    }

}

package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.List;

public class LightEventProcessor implements EventProcessor {

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        handleCommandTypeList(smartHome, handleEvent(smartHome, event));
    }

    private List<CommandType> handleEvent(SmartHome smartHome, SensorEvent event) {
        List<EventHandler> eventHandlerList = new ArrayList<>();

        switch (event.getType()) {
            case LIGHT_ON:
                eventHandlerList.add(new LightOnHandler());
                break;
            case LIGHT_OFF:
                eventHandlerList.add(new LightOffHandler());
                break;
            default:
                // do nothing
        }
        return executeHandlers(smartHome, eventHandlerList, event);
    }

    private List<CommandType> executeHandlers(SmartHome smartHome, List<EventHandler> eventHandlerList, SensorEvent event) {
        List<CommandType> commandTypeList = new ArrayList<>();

        for (EventHandler eventHandler : eventHandlerList) {
            List<CommandType> handlersCommandTypeList = eventHandler.handleEvent(smartHome, event);
            if (handlersCommandTypeList != null) {
                commandTypeList.addAll(handlersCommandTypeList);
            }
        }
        return commandTypeList;
    }

    private void handleCommandTypeList(SmartHome smartHome, List<CommandType> commandTypeList) {
        // do nothing
    }

}

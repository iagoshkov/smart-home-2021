package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.List;

public class DoorEventProcessor implements EventProcessor {

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        handleCommandTypeList(smartHome, handleEvent(smartHome, event));
    }

    private List<CommandType> handleEvent(SmartHome smartHome, SensorEvent event) {
        List<EventHandler> eventHandlerList = new ArrayList<>();

        switch (event.getType()) {
            case DOOR_OPEN:
                eventHandlerList.add(new DoorOpenHandler());
                break;
            case DOOR_CLOSED:
                eventHandlerList.add(new DoorClosedHandler());
                eventHandlerList.add(new HallDoorClosedHandler());
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
        for (CommandType commandType : commandTypeList) {
            switch (commandType) {
                case LIGHT_OFF:
                    new LightOffCommandProducer().produceCommand(smartHome, commandType);
                    break;
                default:
                    // do nothing
            }
        }
    }

}

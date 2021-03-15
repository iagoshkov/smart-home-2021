package ru.sbt.mipt.oop;

import java.util.List;

public class DoorEventProcessor implements EventProcessor {

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        handleCommandTypeList(smartHome, handleEvent(smartHome, event));
    }

    private List<CommandType> handleEvent(SmartHome smartHome, SensorEvent event) {
        EventHandler eventHandler = new EmptyEventHandler();

        switch (event.getType()) {
            case DOOR_OPEN:
                eventHandler = new DoorOpenHandler();
                break;
            case DOOR_CLOSED:
                eventHandler = new DoorClosedHandler();
                break;
            default:
                // do nothing
        }
        return eventHandler.handleEvent(smartHome, event);
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

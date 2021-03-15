package ru.sbt.mipt.oop;

import java.util.List;

public class LightEventProcessor implements EventProcessor {

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        handleCommandTypeList(smartHome, handleEvent(smartHome, event));
    }

    private List<CommandType> handleEvent(SmartHome smartHome, SensorEvent event) {
        EventHandler eventHandler = new EmptyEventHandler();

        switch (event.getType()) {
            case LIGHT_ON:
                eventHandler = new LightOnHandler();
                break;
            case LIGHT_OFF:
                eventHandler = new LightOffHandler();
                break;
            default:
                // do nothing
        }
        return eventHandler.handleEvent(smartHome, event);
    }

    private void handleCommandTypeList(SmartHome smartHome, List<CommandType> commandTypeList) {
        // do nothing
    }

}

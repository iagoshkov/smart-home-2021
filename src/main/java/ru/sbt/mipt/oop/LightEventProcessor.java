package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.List;

public class LightEventProcessor implements EventProcessor {

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
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

        for (EventHandler eventHandler : eventHandlerList) {
            eventHandler.handleEvent(smartHome, event);
        }
    }

}

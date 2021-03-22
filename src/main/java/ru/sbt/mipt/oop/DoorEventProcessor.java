package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.List;

public class DoorEventProcessor implements EventProcessor {

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        List<EventHandler> eventHandlerList = new ArrayList<>();

        switch (event.getType()) {
            case DOOR_OPEN:
                eventHandlerList.add(new DoorOpenHandler());
                break;
            case DOOR_CLOSED:
                eventHandlerList.add(new DoorClosedHandler());
                break;
            default:
                // do nothing
        }

        for (EventHandler eventHandler : eventHandlerList) {
            eventHandler.handleEvent(smartHome, event);
        }
    }

}

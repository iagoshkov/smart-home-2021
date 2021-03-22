package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.List;

public class LightEventProcessor implements EventProcessor {

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        List<Action> eventHandlerList = new ArrayList<>();

        switch (event.getType()) {
            case LIGHT_ON:
                eventHandlerList.add(new LightOnAction(event));
                break;
            case LIGHT_OFF:
                eventHandlerList.add(new LightOffAction(event));
                break;
            default:
                // do nothing
        }

        for (Action action : eventHandlerList) {
            smartHome.execute(action);
        }
    }

}

package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class EventProcessorImpl implements EventProcessor {
    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
            LightState.handleEvent(smartHome, event);
        }
        if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
            // событие от двери
            boolean _check = DoorState.handleEvent(smartHome, event);
            if (_check == true){
                for (Light light : smartHome.getLights()) {
                    room.handleLightEvent(event);
            }
        }
    }
}

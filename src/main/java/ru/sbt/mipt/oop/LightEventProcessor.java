package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class LightEventProcessor implements EventHandler {
    @Override
    public void handleEvent(SmartHome smartHome, SensorEvent event) {
        if (isLightEvent(event)) {
            Action action = object -> {
                if (! (object instanceof Light)) { return; }

                Light asLight = (Light) object;

                if (asLight.getId().equals(event.getObjectId())) {
                    asLight.setOn(event.getType() == LIGHT_ON);
                }
            };
            smartHome.execute(action);
        }
    }

    private boolean isLightEvent(SensorEvent event) {
        return event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF;
    }
}

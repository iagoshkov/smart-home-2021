package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class LightEventProcessor implements EventProcessor {

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if (isLightEvent(event)) {
            smartHome.execute(component -> {
                if (component instanceof Light) {
                    Light light = (Light) component;
                    if (light.getId().equals(event.getObjectId())) {
                        light.setOn(event.getType() == LIGHT_ON);
                        String lightState = (event.getType() == LIGHT_ON) ? "on." : "off.";
                        System.out.println("Light " + light.getId() + " was turned " + lightState);
                    }
                }
            });
        }
    }

    private boolean isLightEvent(SensorEvent event) {
        return event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF;
    }
}


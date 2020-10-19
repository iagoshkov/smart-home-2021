package ru.sbt.mipt.oop.event_handlers;

import ru.sbt.mipt.oop.home.Room;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.light.Light;
import static ru.sbt.mipt.oop.event_handlers.SensorEventType.LIGHT_ON;

public class LightEventHandler {

    private final SensorEvent event;
    private final Light light;

    public LightEventHandler(SensorEvent event, Light light) {
        this.event = event;
        this.light = light;
    }

    public void handleLightEvent() {
        if (light.getId().equals(event.getObjectId())) {
            if (event.getType() == LIGHT_ON) {
                light.setOn(true);
                    } else {
                        light.setOn(false);
                    }
        }
    }

}


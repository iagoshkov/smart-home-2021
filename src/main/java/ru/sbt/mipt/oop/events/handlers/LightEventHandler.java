package ru.sbt.mipt.oop.events.handlers;

import ru.sbt.mipt.oop.actions.*;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.sensor.event.SensorEvent;

import static ru.sbt.mipt.oop.sensor.event.SensorEventType.*;

public class LightEventHandler implements IEventHandler {

    public LightEventHandler() {

    }

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        smartHome.execute(homeComponent -> {
            if (homeComponent instanceof Light) {
                Light light = (Light) homeComponent;
                if (light.getId().equals(event.getObjectId())) {
                    if (event.getType() == LIGHT_ON) {
                        Action action = new LightOnAction(event.getObjectId());
                        light.execute(action);
                    }
                    if (event.getType() == LIGHT_OFF) {
                        Action action = new LightOffAction(event.getObjectId());
                        light.execute(action);
                    }
                }

            }
        });
    }
}

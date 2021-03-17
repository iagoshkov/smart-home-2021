package ru.sbt.mipt.oop;

import java.util.function.Function;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;

public class LightEventHandler implements EventHandler{

    LightEventHandler(SmartHome smartHome){
        this.smartHome = smartHome;
    }

    @Override
    public Action handleEvent(SensorEvent event) {
            return ((component) -> {
                if (component instanceof Light) {
                    Light light = (Light) component;
                    if (light.getId().equals(event.getObjectId())) {
                        if (event.getType() == LIGHT_ON) {
                            light.setOn(true);
                            System.out.println("Light " + light.getId() + " was turned on.");
                        }
                        if (event.getType() == LIGHT_OFF) {
                            light.setOn(false);
                            System.out.println("Light " + light.getId() + " was turned off.");
                        }
                    }
                }
            });
    }

    SmartHome smartHome;
}

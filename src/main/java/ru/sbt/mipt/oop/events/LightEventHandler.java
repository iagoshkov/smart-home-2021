package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.*;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class LightEventHandler implements IEventHandler {

    public LightEventHandler() {

    }

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(event.getObjectId())) {
                    if (event.getType() == LIGHT_ON) {
                        light.setOn();
                        System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
                    }
                    if (event.getType() == LIGHT_OFF) {
                        light.setOff();
                        System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
                    }
                }
            }
        }
    }
}

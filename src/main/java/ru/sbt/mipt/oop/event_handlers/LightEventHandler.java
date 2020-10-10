package ru.sbt.mipt.oop.event_handlers;

import ru.sbt.mipt.oop.home.Room;
import ru.sbt.mipt.oop.home.SmartHome;
import ru.sbt.mipt.oop.light.Light;
import static ru.sbt.mipt.oop.event_handlers.SensorEventType.LIGHT_ON;

public class LightEventHandler {

    public void handleLightEvent(SensorEvent event, Room room) {
        for (Light light : room.getLights()) {
            if (light.getId().equals(event.getObjectId())) {
                if (event.getType() == LIGHT_ON) {
                    light.setOn(true);
                    System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
                } else {
                    light.setOn(false);
                    System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
                }
            }
        }
    }
}
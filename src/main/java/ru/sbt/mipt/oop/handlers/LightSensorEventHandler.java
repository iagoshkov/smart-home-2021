package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.Light;
import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.sensors.SensorEvent;

import java.util.Collection;

import static ru.sbt.mipt.oop.sensors.SensorEventType.LIGHT_ON;

public class LightSensorEventHandler implements SensorEventHandler {
    private final Collection<Room> rooms;
    private final SensorEvent event;

    public LightSensorEventHandler(SmartHome smartHome, SensorEvent event) {
        this.rooms = smartHome.getRooms();
        this.event = event;
    }

    @Override
    public void handleEvent() {
        // событие от источника света
        for (Room room : rooms) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(event.getObjectId())) {
                    if (event.getType() == LIGHT_ON) {
                        handleLightOn(room, light);
                    } else {
                        handleLightOff(room, light);
                    }
                }
            }
        }
    }

    private void handleLightOn(Room room, Light light) {
        light.setOn(true);
        System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
    }

    private void handleLightOff(Room room, Light light) {
        light.setOn(false);
        System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
    }
}
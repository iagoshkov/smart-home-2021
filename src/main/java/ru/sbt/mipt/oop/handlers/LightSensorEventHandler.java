package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Light;
import ru.sbt.mipt.oop.Room;
import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.sensors.SensorEvent;

import java.util.Collection;

import static ru.sbt.mipt.oop.sensors.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.sensors.SensorEventType.LIGHT_ON;

public class LightSensorEventHandler implements SensorEventHandler {
    private final Collection<Room> rooms;
    private final SensorEvent event;
    private final SmartHome smartHome;

    public LightSensorEventHandler(SmartHome smartHome, SensorEvent event) {
        this.smartHome = smartHome;
        this.rooms = smartHome.getRooms();
        this.event = event;
    }

    @Override
    public void handleEvent() {
        // событие от источника света
        if (event.getType() != LIGHT_ON && event.getType() != LIGHT_OFF) {
            return;
        }

        boolean turnResult = (event.getType() == LIGHT_ON);
        String eventId = event.getObjectId();

        Action action = (o) -> {
            if (o instanceof Light) {
                Light light = (Light) o;
                String lightId = light.getId();
                if (lightId.equals(eventId)) {
                    light.setOn(turnResult);
                    System.out.print("Light " + light.getId() + " was turned ");
                    System.out.println(turnResult ? "on." : "off.");
                }
            }
        };
        smartHome.doAction(action);
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

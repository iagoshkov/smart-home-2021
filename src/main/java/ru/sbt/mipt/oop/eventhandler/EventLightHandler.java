package ru.sbt.mipt.oop.eventhandler;

import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.SensorEventType;
import ru.sbt.mipt.oop.objects.Light;
import ru.sbt.mipt.oop.objects.Room;
import ru.sbt.mipt.oop.objects.SmartHome;


public class EventLightHandler implements EventHandler{
    private final SmartHome smartHome;

    public EventLightHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    private Light findLightByID(String id) {
        final Light[] light = {null};
        smartHome.execute(lightCandidate -> {
            if (lightCandidate instanceof Light && ((Light) lightCandidate).getId().equals(id)) {
                light[0] = (Light) lightCandidate;
            }
        });
        return light[0];
    }

    private Room findRoomByLight(String id) {
        final Room[] r = {null};
        smartHome.execute(roomCandidate -> {
            if (roomCandidate instanceof Room) {
                ((Room) roomCandidate).execute(lightCandidate -> {
                    if (lightCandidate instanceof Light && ((Light) lightCandidate).getId().equals(id)) {
                        r[0] = (Room) roomCandidate;
                    }
                });
            }
        });
        return r[0];
    }

    private void handleLightOffEvent(SensorEvent event) {
        Room room = findRoomByLight(event.getObjectId());
        Light light = findLightByID(event.getObjectId());
        if (light == null) return;
        light.setOn(false);
        System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
    }

    private void handleLightOnEvent(SensorEvent event) {
        Room room = findRoomByLight(event.getObjectId());
        Light light = findLightByID(event.getObjectId());
        if (light == null) return;
        light.setOn(true);
        System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
    }

    @Override
    public void handleEvent(SensorEvent event) {
        if (event.getType() == SensorEventType.LIGHT_OFF) {
            handleLightOffEvent(event);
        } else if (event.getType() == SensorEventType.LIGHT_ON) {
            handleLightOnEvent(event);
        }
    }
}

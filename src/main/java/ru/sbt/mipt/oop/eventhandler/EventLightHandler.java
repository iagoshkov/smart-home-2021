package ru.sbt.mipt.oop.eventhandler;

import ru.sbt.mipt.oop.event.SensorEvent;
import ru.sbt.mipt.oop.event.SensorEventType;
import ru.sbt.mipt.oop.objects.Light;
import ru.sbt.mipt.oop.objects.Room;
import ru.sbt.mipt.oop.objects.SmartHome;

import java.util.concurrent.atomic.AtomicReference;

public class EventLightHandler implements EventHandler{
    private final SmartHome smartHome;

    public EventLightHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    private Light findLightByID(String id) {
        AtomicReference<Light> light = new AtomicReference<>();
        smartHome.execute(lightCandidate -> {
            if (lightCandidate instanceof Light && ((Light) lightCandidate).getId().equals(id)) {
                light.set((Light) lightCandidate);
            }
        });
        return light.get();
    }

    private Room findRoomByLight(String id) {
        AtomicReference<Room> r = new AtomicReference<>();
        smartHome.execute(roomCandidate -> {
            if (roomCandidate instanceof Room) {
                ((Room) roomCandidate).execute(lightCandidate -> {
                    if (lightCandidate instanceof Light && ((Light) lightCandidate).getId().equals(id)) {
                        r.set((Room) roomCandidate);
                    }
                });
            }
        });
        return r.get();
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

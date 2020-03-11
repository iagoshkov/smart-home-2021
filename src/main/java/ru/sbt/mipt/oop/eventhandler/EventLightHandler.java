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

    private void handleLightOffEvent(SensorEvent event) {
        Room room = smartHome.findRoomByLight(event.getObjectId());
        Light light = smartHome.findLightByID(room, event.getObjectId());
        if (light == null) return;
        light.setOn(false);
        System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
    }

    private void handleLightOnEvent(SensorEvent event) {
        Room room = smartHome.findRoomByLight(event.getObjectId());
        Light light = smartHome.findLightByID(room, event.getObjectId());
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

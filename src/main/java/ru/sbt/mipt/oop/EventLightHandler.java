package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class EventLightHandler implements EventHandler{
    private static SmartHome smartHome;

    public EventLightHandler(SmartHome smartHome) {
        EventLightHandler.smartHome = smartHome;
    }

    public static void handleEvent(SensorEvent event) {
        for (Room room : smartHome.getRooms()) {
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
}
